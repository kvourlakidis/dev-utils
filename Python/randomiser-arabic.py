#!/usr/bin/env python
import random
import sys

WORD_LENGTH_MIN = 1
WORD_LENGTH_MAX = 10

SENTENCE_LEN_MIN = 1
SENTENCE_LEN_MAX = 5

SPACE = unichr(32)
FULL_STOP_LATIN = unichr(46)
FULL_STOP_ARABIC = unichr(1748)

UTF_DEC_ARABIC = []
UTF_DEC_ARABIC += range(1569, 1595)
UTF_DEC_ARABIC += range(1600, 1622)
UTF_DEC_ARABIC += range(1632, 1642)
UTF_DEC_ARABIC += range(1649, 1757)
UTF_DEC_ARABIC += range(1759, 1769)
UTF_DEC_ARABIC += range(1770, 1791)

UTF_DEC_ARABIC_PUNCT = []
UTF_DEC_ARABIC_PUNCT += [ 1548, 1563, 1567]
UTF_DEC_ARABIC_PUNCT += range(1642, 1649)
# UTF_DEC_ARABIC_PUNCT += [ 1757, 1758, 1769 ] # these are only present in the Qur'an

# exclude certain characters

def getArabicChar():
    return getRandomFromList(UTF_DEC_ARABIC)

def getArabicPunct():
    return getRandomFromList(UTF_DEC_ARABIC_PUNCT)

def getArabicWord():
    length = random.randrange(WORD_LENGTH_MIN, WORD_LENGTH_MAX)
    s = ""
    for i in range(length):
        s += getArabicChar()
    return s

def getArabicSentence():
    numWords = random.randrange(SENTENCE_LEN_MIN, SENTENCE_LEN_MAX)
    s = ""
    for i in range(numWords):
        s += getArabicWord()
        dice = random.randint(0,100)
        if (dice > 90):
            s += getArabicPunct()
        else:
            s += SPACE
    if (random.randint(0,1)):
        s = s.strip() + FULL_STOP_ARABIC
    return s.strip()

def mutateArabicString(s):
    dice = random.randint(0,100)
    r = random.randint(0,len(s))
    ins = u''
    if (dice > 80):
        ins = getArabicChar()
    if (dice > 95):
        ins = getArabicPunct()
    if (dice > 99):
        ins = SPACE + getArabicWord() + SPACE
    newString = s[0:r] + ins + s[r:]
    return newString

def getRandomFromList(inList):
    i = random.choice(inList)
    return unichr(i)

def main(argvs):
    for i in range(100): 
        print getArabicSentence()

if (__name__ == "__main__"):
    main(sys.argv[1:])
