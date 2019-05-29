String separator = ','
String newLine = System.getProperty('line.separator')
int numberOfFields = 16

String filepath1 = '/Users/kyrilosvourlakidis/Projects/dev-utils/Groovy/LocationSmall.csv'
String filepath2 = '/Users/kyrilosvourlakidis/Projects/dev-utils/Groovy/New.csv'
println "Reading from file: $filepath1"
println "Writing to file: $filepath2"
def input = new File(filepath1)
def output = new File(filepath2)
// empty the file
output.write("")

def lines = input.readLines()
println "Number of lines: $lines.size"

lines.eachWithIndex { line, lineNumber -> 
    // skip the header
    if (lineNumber == 0) {
        output.append(line + newLine)
    } else {
        // split on the separator
        def fields = line.split(separator)
        if (fields.size() != numberOfFields) {
            System.err.println "Line $lineNumber did not have the expected number of fields"
            return
        }
        def newField = "Kris has to fix this bit ${fields[-1]} ${fields[-2]}"
        output.append("$line,$newField$newLine")
    }
}