#!/usr/bin/env python

import sys
import os
import unicodecsv as csv
from sets import Set
import random
import arabic
import hebrew
import io

SEPARATOR = ','
ESCAPECHAR = '"'
ENCODING = 'utf-8'

COLNAME_FIRSTNAME = "P_FIRST_GIVEN_NAME"
COLNAME_MIDDLENAME = "P_MIDDLE_NAME"
COLNAME_LASTNAME = "P_FAMILY_NAME"
COLNAME_INFORMATION = "P_ADDITIONAL_INFORMATIO"
COLNAME_DESCRIPTION = "P_DESCRIPTION"

NEWFILE_NAME_POSTFIX = "-new"

NAMES_FILE = "names-arabic.txt"
# NAMES_FILE = "names-hebrew.txt"
SENTENCES_FILE = "full_arabic_sentences.txt"
# SENTENCES_FILE = "full_hebrew_sentences.txt"

FIRSTNAMES = Set()
PHRASES = Set()

def main(argvs):
    files = []
    for file in os.listdir(os.getcwd()):
        if file in argvs:
            files.append(file)

    for fileName in files:
        newContents = readAndReplaceContents(fileName)
        if newContents:
            newName = fileName.split(".")[0] + NEWFILE_NAME_POSTFIX + "." + fileName.split(".")[1]
            writeContents(newName, newContents)
    return 0 # /main()

def readAndReplaceContents(fileName):
    newRows = []
    with open(fileName,'r') as fIn:
        colIndexFirstName = 11
        colIndexMiddleName = 17
        colIndexLastName = 16
        colIndexInformation = 18
        colIndexDescription = 7
        columnsIndexed = False
        numLines = 0


        print "Reading: {}".format(fIn.name)
        spamreader = csv.reader(fIn, 
            delimiter=SEPARATOR, 
            quotechar=ESCAPECHAR,
            encoding=ENCODING)
        
        for row in spamreader:
            if (not columnsIndexed):
                # first row is column names
                columnNames = row
                if (COLNAME_FIRSTNAME in columnNames):
                    colIndexFirstName = columnNames.index(COLNAME_FIRSTNAME)
                if (COLNAME_MIDDLENAME in columnNames):
                    colIndexMiddleName = columnNames.index(COLNAME_MIDDLENAME)
                if (COLNAME_LASTNAME in columnNames):
                    colIndexLastName = columnNames.index(COLNAME_LASTNAME)
                if (COLNAME_INFORMATION in columnNames):
                    colIndexInformation = columnNames.index(COLNAME_INFORMATION)
                if (COLNAME_DESCRIPTION in columnNames):
                    colIndexDescription = columnNames.index(COLNAME_DESCRIPTION)

                # if (colIndexFirstName == -1 or colIndexMiddleName == -1 or colIndexLastName == -1 or colIndexInformation == -1):
                    # print "No columns to replace for {}".format(deDupeColumn,fIn.name)
                    # return

                columnsIndexed = True
                newRows.append(columnNames)
                continue # skip to the data

            # print "colIndexFirstName=" + str(colIndexFirstName)
            # print "colIndexMiddleName=" + str(colIndexMiddleName)
            # print "colIndexLastName=" + str(colIndexLastName)
            # print "colIndexInformation=" + str(colIndexInformation)
            # print "colIndexDescription=" + str(colIndexDescription)
            # break

            newRow = row
            if (colIndexFirstName):
                newRow[colIndexFirstName] = generateFirstName()
            if (colIndexLastName):
                newRow[colIndexLastName] = generateLastName()
            if (colIndexInformation):
                newRow[colIndexInformation] = generateInformation()
            if (colIndexDescription):
                newRow[colIndexDescription] = generateDescription()

            newRows.append(newRow)
            numLines += 1
            if (numLines % 10000 == 0):
                print "Read in: {} lines".format(numLines)
                # break # for testing
    print "Finished parsing {}".format(fileName)
    return newRows

def writeContents(fileName, contents):
    with open(fileName,'w') as fOut:
        print "Writing into: {}".format(fOut.name)
        spamwriter = csv.writer(fOut, 
            delimiter=SEPARATOR, 
            quotechar=ESCAPECHAR,
            encoding=ENCODING)
        numLines = 0
        for row in contents:
            spamwriter.writerow(row)
            numLines += 1
        print "Finished writing: {} lines".format(numLines)
    return

def generateFirstName():
    if (not FIRSTNAMES):
        parseNamesFile(NAMES_FILE, FIRSTNAMES)
    name = random.sample(FIRSTNAMES, 1)[0]
    return arabic.mutateArabicString(name)
    # return hebrew.mutateHebrewString(name)

def generateMiddleName():
    return generateFirstName()

def generateLastName():
    return generateFirstName()

def generateInformation():
    if (not PHRASES):
        parseSentencesFile(SENTENCES_FILE, PHRASES)
    sentence = random.sample(PHRASES, 1)[0]
    return arabic.mutateArabicString(sentence)
    # return hebrew.mutateHebrewString(sentence)

def generateDescription():
    return arabic.getArabicSentence()
    # return hebrew.getHebrewSentence()

def parseNamesFile(fileName, namesSet):    
    with io.open(fileName,'r', encoding=ENCODING) as fIn:
        for line in fIn:
            if line.startswith('#'):
                continue
            if (line.strip()):
                names = [ name.strip() for name in line.split(SEPARATOR) ]
                if (names):
                    namesSet.update(names)

def parseSentencesFile(fileName, phraseSet):    
    with io.open(fileName,'r', encoding=ENCODING) as fIn:
        for line in fIn:
            if line.startswith('#'):
                continue
            if (line.strip()):
                phrase = line.strip()
                if (phrase):
                    phraseSet.add(phrase)


if (__name__ == "__main__"):
    # reload(sys)  
    # sys.setdefaultencoding('utf8')
    parseNamesFile(NAMES_FILE, FIRSTNAMES)
    parseSentencesFile(SENTENCES_FILE, PHRASES)
    main(sys.argv[1:])

# END OF SCRIPT
