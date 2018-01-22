#!/usr/bin/env python

def fRead(filepath):
  content_hash = {}
  with open(filepath, 'r') as f:
    linelist = []
    for line in f:
      linelist = line.split('\t')
      del linelist[-1]
      content_hash[linelist[0]] = linelist[1:]
  return content_hash

def fWrite(filepath, content):
  #print 'Attempting to write to disc: %s<br>' % filepath
  with open(filepath, 'w') as f:
    #print 'Writing to disc...'
    for key, value in content.items():
      f.write(str(key) + '\t')
      for word in value:
        f.write(str(word) + '\t')
      f.write('\n')
  #print 'Done writing file to disc: %s' % filepath
