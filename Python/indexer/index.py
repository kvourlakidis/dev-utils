#!/usr/bin/evn python

from re import compile as recomp
import os

pattern = recomp("[a-zA-Z][a-zA-Z0-9]*")


def fileIndex(inputfile, window, overlap):
  #print 'Index starting on %s <br>' % inputfile
  jump = window - overlap
  with open(inputfile, 'r') as f:
    #print 'File opened successfully<br>'
    content = pattern.findall(f.read())
  filestring = ''
  for i in content:
    if i[0].lower() <= 'f': filestring += 'A'
    elif i[0].lower() <= 'l': filestring += 'C'
    elif i[0].lower() <= 'r': filestring += 'G'
    else: filestring += 'T'
  a = len(filestring)
  keylist = []
  for i in range(0,a-window+1,jump):
    key = filestring[i:i+window]
    if key not in keylist:
      keylist.append(filestring[i:i+window])
  keylist = sorted(keylist)
  #print 'Index finished' 
  return keylist

def sourceIndex(directory,window,overlap,start,finish):

  jump = window-overlap
  filestring_hash = {}
  filekey_hash = {}
  filelist = sorted(os.listdir(directory))
  #start  = int(round(float(len(filelist))/100*start))
  #finish = int(round(float(len(filelist))/100*finish))
  for item in filelist[start:finish]:
    itempath = directory+item
    if item.startswith("source") and item.endswith(".txt"):
      with open(itempath,'r') as f:
        content = pattern.findall(f.read())
      filestring = ''
      for i in content:
        if i[0].lower() <= 'f': filestring += 'A'
        elif i[0].lower() <= 'l': filestring += 'C'
        elif i[0].lower() <= 'r': filestring += 'G'
        else: filestring += 'T'
      filestring_hash.update({item:filestring})

  for key, value in filestring_hash.items():
    a = len(value)
    for i in range(0,a-window+1,jump):
      val = value[i:i+window]
      if val in filekey_hash:
        filekey_hash[val].extend([key, i, i+window-1])
      else:
        filekey_hash.update({val: [key, i, i+window-1]})
  #print 'Finished full index on %s files.' % len(filestring_hash)
  return filekey_hash

if __name__ == "__main__":
  res = fileIndex("/home/ec2-user/Documents/susp-text-PAN11/suspicious-document00010.txt", 10, 4)
  for line in res:
    print line
