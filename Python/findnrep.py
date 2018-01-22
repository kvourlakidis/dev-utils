#!/usr/bin/env python

import os
import sys

def findAndReplace(fileString,changesHash):
	for old,new in changesHash.items():
		print 'Replacing {} with {}!'.format(old,new)
		fileString = fileString.replace(old,new)
	return fileString

repHash = {
	'currency': 'blow',
	'is': 'was'
	}

with open('text','r') as f:
	oldFile = f.read()

newFile = findAndReplace(oldFile,repHash)

with open('text','w') as f:
	f.write(newFile)



