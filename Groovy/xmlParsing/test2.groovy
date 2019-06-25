import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import groovy.xml.StreamingMarkupBuilder

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public static File makeFile(def file) {
    file = asFile(file)
    def parentFile = file.getParentFile()
    if (parentFile) {
        parentFile.mkdirs()
    }
    return file
}

public static File asFile(def file) {
    if (!(file instanceof File)) file = new File(file)
    return file
}

def writeXMLFile(def file, def rootNode, String encoding = "UTF-8") {
    file = makeFile(file)
    file.withWriter(encoding) { out ->
        XmlUtil.serialize(new StreamingMarkupBuilder().bind { mkp.yield rootNode }, out)
    }
}



    private boolean updateSolrLog4jLogLevel(GPathResult log4jElement) {
        // Reduce the default log level for the request handler so that it doesn't log every request.
        /*
        Log levels:
            FINEST  Reports everything.
            FINE    Reports everything but the least important messages.
            CONFIG  Reports configuration errors.
            INFO    Reports everything but normal status.
            WARN    Reports all warnings.
            SEVERE  Reports only the most severe warnings.
            OFF     Turns off logging.
            UNSET   Removes the previous log setting.
         */
        String loggedClass = 'org.apache.solr.core.SolrCore.Request'
        String logLevel = 'finest'
        boolean updated = false
        def requestLoggerElement = log4jElement.Loggers.AsyncLogger.find { it.@name == loggedClass }
        if (requestLoggerElement.size() == 0) {
            // <AsyncLogger name="org.apache.solr.core.SolrCore.Request" level="warn" additivity="false">
            log4jElement.Loggers.appendNode {
                AsyncLogger(name: loggedClass, level: logLevel, additivity: 'false')
            }
            updated = true
        } else {
            if (requestLoggerElement.@level != logLevel) {
                requestLoggerElement.replaceNode {
                    AsyncLogger(name: loggedClass, level: logLevel, additivity: 'false')
                }
                updated = true
            }
        }
        return updated
    }

String xml = "log4jtest.xml"
GPathResult log4jXmlTree = new XmlSlurper().parse(new File(xml))

String contentOld = new File(xml).text
boolean updated = updateSolrLog4jLogLevel(log4jXmlTree)
String contentNew = XmlUtil.serialize(log4jXmlTree)

Pattern pattern = Pattern.compile("<Loggers>[\\s\\S]*?<\\/Loggers>")
Matcher matcherOld = pattern.matcher(contentOld)
Matcher matcherNew = pattern.matcher(contentNew)

if (matcherOld.find() & matcherNew.find()) {
    // println matcherNew.group()
    // build up the new String
    String out = contentOld.substring(0, matcherOld.start()) + matcherNew.group() + contentOld.substring(matcherOld.end(), contentOld.length())
    println out
}

if (updated) {
    // writeXMLFile(xml, log4jXmlTree)

    println "Solr ${xml}'s updated."
}
