#!/usr/bin/env python
import random
import sys

WORD_LENGTH_MIN = 1
WORD_LENGTH_MAX = 10

SENTENCE_LEN_MIN = 1
SENTENCE_LEN_MAX = 5

SPACE = unichr(32)
FULL_STOP_LATIN = unichr(46)
FULL_STOP_HEBREW = FULL_STOP_LATIN

UTF_DEC_HEBREW = []
UTF_DEC_HEBREW += range(1488, 1515)
UTF_DEC_HEBREW += range(1520, 1523)


UTF_DEC_HEBREW_PUNCT = []
UTF_DEC_HEBREW_PUNCT += range(1425, 1442)
UTF_DEC_HEBREW_PUNCT += range(1443, 1466)
UTF_DEC_HEBREW_PUNCT += range(1467, 1477)
UTF_DEC_HEBREW_PUNCT += [ 1523, 1524 ]

# exclude certain characters

def getHebrewChar():
    return getRandomFromList(UTF_DEC_HEBREW)

def getHebrewPunct():
    return getRandomFromList(UTF_DEC_HEBREW_PUNCT)

def getHebrewWord():
    length = random.randrange(WORD_LENGTH_MIN, WORD_LENGTH_MAX)
    s = ""
    for i in range(length):
        s += getHebrewChar()
    return s

def getHebrewSentence():
    numWords = random.randrange(SENTENCE_LEN_MIN, SENTENCE_LEN_MAX)
    s = ""
    for i in range(numWords):
        s += getHebrewWord()
        dice = random.randint(0,100)
        if (dice > 90):
            s += getHebrewPunct()
        else:
            s += SPACE
    if (random.randint(0,1)):
        s = s.strip() + FULL_STOP_HEBREW
    return s.strip()

def mutateHebrewString(s):
    dice = random.randint(0,100)
    r = random.randint(0,len(s))
    ins = u''
    if (dice > 80):
        ins = getHebrewChar()
    if (dice > 95):
        ins = getHebrewPunct()
    if (dice > 99):
        ins = SPACE + getHebrewWord() + SPACE
    newString = s[0:r] + ins + s[r:]
    return newString

def getRandomFromList(inList):
    i = random.choice(inList)
    return unichr(i)

def main(argvs):
    for i in range(100): 
        print getHebrewSentence()

if (__name__ == "__main__"):
    main(sys.argv[1:])
