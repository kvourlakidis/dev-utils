#!/usr/bin/env python

import sys
import os


def main(argvs):



	if argvs:
		print 'Have args!'
		for argv in argvs:
			print argv


	if not sys.stdin.isatty():
		print 'Have stdin!'
		pipeLine = sys.stdin
		for line in pipeLine:
			fPath = line.strip()
			print fPath
			if not os.path.isfile(fPath):
				print fPath + ' is not a file'
				continue

			with open(fPath,'r') as f:
				oldFile = f.read()
			
			newFile = addDisplayName(oldFile)

#			with open(fPath+'_new','w') as f:
			with open(fPath,'w') as f:
				f.write(newFile)

	else:
		print("nothing in stdin")

	#
	# /main()
	#


def addDisplayName(oldFile):
	
	import copy 

	print 'SOF'
	oldFlines = oldFile.split('\r\n')
	newFlines = copy.deepcopy(oldFlines)
	insLines = 0
	insertFlag = False
	for idx,line in enumerate(oldFlines):
		if 'jobsToCreate.addAll' in line:
			insertFlag = True
			print idx+1,line
		if insertFlag:
			l = line.split(' ')
			if 'name:' in l:
				if 'displayName:' in oldFlines[idx+1]:
					continue
				indNum = line.index('name:')
				buildName = l[l.index('name:')+1].strip().strip(',').strip('\'\"')
				if buildName.startswith('h'):
					# newBuildName = '_'.join(buildName.split('_')[1:])
					# print buildName + ' ---> ' + newBuildName
					# findAndReplace(newFlines,{buildName:newBuildName})
					print buildName
					pass
				else:
					continue
				lineToInsert = ' '*indNum + 'displayName: ' + '\'{}\''.format(buildName) + ','
				insLines += 1
				newFlines.insert(idx+insLines,lineToInsert)
	print idx+1
	print 'EOF'
	newFile = '\r\n'.join(newFlines)
	return newFile



def findAndReplace(fileLines,changesHash):
	for old,new in changesHash.items():
		a = "'" + old + "'"
		b = '"' + old + '"'
		c = "'" + new + "'"
		for i in range(0,len(fileLines)):
			line = fileLines[i]
			line = line.replace(a,c)
			line = line.replace(b,c)
			fileLines[i] = line


# def writeFile(flines,fPath):
#	print 'Writing to file...'
#	print fPath
#	with open(fPath,'w') as f:
#		for line in flines:
#			f.write(line)
#		f.close()
#	print 'Finished writing to file'


if (__name__ == "__main__"):

	main(sys.argv[1:])

