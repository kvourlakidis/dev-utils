import HttpHelpers
import static HttpHelpers.Constants as Constants
//
// main
//
println "start"
submitPeople(100, 10_000)
println "end"

//
// end of main
//

def submitPeople(Integer nRequests, Integer nPerRequest) {
    for (int i=0; i<nRequests; i++) {
        def submitRequest = HttpHelpers.createPostRequest(
            Constants.BASE_URL_OPAL + Constants.SUBMIT,
            HttpHelpers.getMessageBody(getSubmitBodyMap(nPerRequest)),
            Constants.CONTENT_TYPE_SUBMIT)
        HttpHelpers.handleResponse(submitRequest)
    }
}
def getSubmitBodyMap(int numEntities = 1) {
    def uniqueID = UUID.randomUUID().toString()
    def recordMapping = [
        typeId: "ET5",
        provenance: [
            originId: [
                originIdType: [ constantValue: "OI.TEST" ],
                originIdKeys: [[column: 0], [column: 1]]
            ]
        ],
        properties: [
            PER3:  [columns: [[column: 2]]]
            ,PER4:  [columns: [[column: 3]]]
            ,PER6:  [columns: [[column: 4]]]
            ,PER34: [columns: [[column: 5]]]
            // ,PER8:  [columns: [[column: 6]]]
            // ,PER99: [columns: [[column: 7]]]
        ],
        accessDimensions: [
            'SD-SL': [ constantValue: "UC" ],
            'SD-SC': [ constantValue: "OSI" ]
        ]
    ]
    def sourceRecordData = []
    for (int i=0; i<numEntities; i++) {
        def name = getRandomFullName()
        def mark = getRandomSentence()
        // def id1 = UUID.randomUUID().toString()
        // def id2 = UUID.randomUUID().toString()
        sourceRecordData.add([
            uniqueID            // 0
            ,i                  // 1
            ,name[0]            // 2
            ,name[1]            // 3
            ,name[-1]           // 4
            ,mark               // 5
            // ,id1                // 6
            // ,id2                // 7
        ])
    }
    def payload = [
        recordGroupRequired: true,
        purpose: "UPLOAD",
        sourceRecordMappingAndData: [[recordMapping: recordMapping, sourceRecordData: sourceRecordData]],
        noteChangesets: []
    ]
    return payload
}

class Statics {
    static String namesFile = 'names.txt'
    static String wordsFile = 'words.txt'
    static List names
    static List words
    static Random random = new Random()
    static List titles = ['Mr.', 'Mrs.', 'Ms.', 'Miss', 'Master', 'Madam']
    static {
        names = loadFromFileIntoList(namesFile, ',')
        words = loadFromFileIntoList(wordsFile, '\n')
    }

    static List loadFromFileIntoList(String fileName, String splitter) {
        def things = []
        def contents = new File(fileName).text
        contents.split(splitter).each { thing ->
            def trimmed = thing.trim()
            if (trimmed[0] == '"') trimmed = trimmed[1..-1]
            if (trimmed[-1] == '"') trimmed = trimmed[0..-2]
            things.add(trimmed)
        }
        return things
    }
}

def getRandom(nList) {
    def r = Statics.random
    def ind = r.nextInt(nList.size())
    return nList[ind]
}

def getRandomName() {
    def name = getRandom(Statics.names)
    return name.toLowerCase().capitalize()
}

def getRandomFullName() {
    def r = Statics.random
    def num = 2 + r.nextInt(3)
    def name = [getRandomTitle()]
    num.times { name.add(getRandomName()) }
    return name
}

def getRandomTitle() {
    return Statics.titles[Statics.random.nextInt(Statics.titles.size())]
}

def getRandomWord() {
    return Statics.words[Statics.random.nextInt(Statics.words.size())]
}

def getRandomSentence() {
    int max = 20
    def sentence = getRandomWord().capitalize()
    int len = Statics.random.nextInt(max)
    len.times { sentence += (' ' + getRandomWord()) }
    sentence = sentence + '.'
    return sentence
}