#!/usr/bin/env groovy

import java.text.SimpleDateFormat

if (args.length != 1) {
    println "Wrong number of arguments. Usage:"
    println "./Parser.groovy FILE_NAME"
    return
}

Closure<String> solrIndexSubscriber = { words -> 
    String className, batchId, numberOfRows, csvWriteTime, indexWriteTime, timeTaken
    className = 'SolrIndexSubscriber'
    if (words.length == 35) {
        batchId = words[18].replaceAll("[:,']","")
        csvWriteTime = words[19]
        indexWriteTime = words[28]
        timeTaken = words[10]
        return "${className},${batchId},${numberOfRows},${csvWriteTime},${indexWriteTime},${timeTaken}\n"
    }
    return ""
}

Closure<String> infoStoreRecordGroupIndexerWriter = { words -> 
    String className, batchId, numberOfRows, csvWriteTime, indexWriteTime, timeTaken
    className = 'InfoStoreRecordGroupIndexerWriter'
    if (words.length == 23) {
        numberOfRows = words[16]
        csvWriteTime = words[21]
        batchId = words[19].split("_")[-1]
        return "${className},${batchId},${numberOfRows},${csvWriteTime},${indexWriteTime},${timeTaken}\n"
    }
    return ""
}

Map<String, Closure<String>> classesToLog = [
    "com.i2group.disco.infostore.sync.internal.InfoStoreRecordGroupIndexerWriter" : infoStoreRecordGroupIndexerWriter,
    "com.i2group.disco.search.indexing.internal.SolrIndexSubscriber" : solrIndexSubscriber
]

String inName = args[0]
File inFile = new File(inName)

String outName = new SimpleDateFormat("yy-MM-dd'T'hh-mm").format(new Date())
File outFile = new File(outName + '.csv')
outFile.write("className,batchId,numberOfRows,csvWriteTime,indexWriteTime,timeTaken\n")

BufferedReader br = new BufferedReader(new FileReader(inFile))
String thisLine = null;
while ((thisLine = br.readLine()) != null) {
    String[] words = thisLine.split(" ")
    String className = words[7]
    if (classesToLog.containsKey(className)) {
        def line = classesToLog.get(className).call(words)
        outFile.append(line)
    }
}

println "Finished writing to file: ${outFile.absolutePath}"
