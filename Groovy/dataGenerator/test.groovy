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
    def sentence = [getRandomWord()]
    int len = Statics.random.nextInt(max)
    len.times { sentence.add(getRandomWord()) }
    sentence.add('.')    
    return sentence
}
