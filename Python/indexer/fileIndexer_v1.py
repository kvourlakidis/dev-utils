#!/usr/bin/python

import sys 
import re

def main(argv): 
  window = 10
  overlap = 4
  jump = window - overlap
  stringlist = []  
  pattern = re.compile("[a-zA-Z][a-zA-Z0-9]*")
  filestring = ''
  for line in sys.stdin: 
    for word in pattern.findall(line):
      if word[0].lower() <= 'f':
    	  filestring += 'A'
      elif word[0].lower() <= 'l':
    	  filestring += 'C'
      elif word[0].lower() <= 'r':
	      filestring += 'G'
      else:
    	  filestring += 'T'

  a = len(filestring)
  for i in range(0,a-window+1,jump):
    key = filestring[i:i+window]
    print "LongValueSum:" + key.lower() + "\t" + "1" 

if __name__ == "__main__": 
    main(sys.argv)
