#!/usr/bin/env python

import sys
import os

def main(argvs):

	if argvs:
		findList = []
		print "Looking for:"
		for argv in argvs:
			t = argv.strip()
			findList.append(t)
			print t
		print ""
	else:
		print "No argvs, quitting..."
		return 1
	
	if not sys.stdin.isatty():
		pipeline = sys.stdin
		files = []
		print "Searching the following files:"
		for line in pipeline:
			fPath = line.strip()
			if not os.path.isfile(fPath):
				print "Warning: " + fPath + " is not a file!"
			else:
				files.append(fPath)
				print fPath
		print "Number of files: {}\n".format(len(files))
		
		for fPath in files:
			with open(fPath, 'r') as f:
				thisFile = f.read()
			
			findStrings(thisFile, findList)
			
	else:
		print "Nothing in STDIN pipeline, quitting..."
		return 1

def findStrings(strToSearch,searchStrings):
	fileLines = strToSearch.split('\n')
	for i in range(len(fileLines)):
		fileLine = fileLines[i]
		for searchString in searchStrings:
			if searchString in fileLine:
				print "{:04d}   {}".format( i, fileLine.strip('[ ]') )
				break
		
if ( __name__ == "__main__" ):

	main(sys.argv[1:])
