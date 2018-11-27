#!/usr/bin/env python

import sys
import os


def main(argvs):

	if argvs:
		fnrDict = {}
		print 'Replacing:\t\t\t\tWith:'
		for argv in argvs:
			if argv.startswith('h'):
				fnrDict[argv] = '_'.join(argv.split('_')[1:])
				print argv + '\t\t=>\t\t' + fnrDict[argv] + '\n\n'
	else:
		print 'No argvs, quitting...'
		return 1
	
	if not sys.stdin.isatty():
		pipeLine = sys.stdin
		for line in pipeLine:
			fPath = line.strip()
			# print 'Searching the following files:'
			# print fPath + '\n'
			if not os.path.isfile(fPath):
				print 'Warning: ' + fPath + ' is not a file!'
				continue

			with open(fPath,'r') as f:
				oldFile = f.read()
			
			if not fnrDict:
				print 'Illegal arguments, quitting...'
				return 1
				
			newFile = findAndReplace(oldFile,fnrDict)
			
			if oldFile == newFile:
			# 	print 'No changes to: \n' + fPath + '\n'
				continue
			else:
				# with open(fPath+'_new','w') as f:
				with open(fPath,'w') as f:
					print 'Writing changes to: \n' + os.path.realpath(f.name) + '\n\n'
					f.write(newFile)

	else:
		print("Nothing in STDIN pipeline")
		return 1
		
	return 0 # /main()

def findAndReplace(fileString,changesHash):
	fileLines = fileString.split('\r\n')
	for old,new in changesHash.items():
		a = "'" + old + "'"
		b = '"' + old + '"'
		c = "'" + new + "'"
		for i in range(0,len(fileLines)):
			line = fileLines[i]
			if 'displayName' in line:
				continue
			else:
				pass
			line = line.replace(a,c)
			line = line.replace(b,c)
			fileLines[i] = line
	return '\r\n'.join(fileLines)

if (__name__ == "__main__"):

	main(sys.argv[1:])

