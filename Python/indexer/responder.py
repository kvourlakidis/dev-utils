#!/usr/bin/env python

import sys
import os
import index
import match
import writer
import httplib
import json
from time import time
startsec = time()
localpath = '/home/ec2-user/Documents/'
#localpath = '/home/kirill/Documents/COMM034/'
srcfilepath = localpath + 'source-text-PAN11/'

def main(a,b,c,susp,d,e):
  window = int(a)
  overlap = int(b)
  mdist = int(c)
  start = int(d)
  finish = int(e)
  filelist = sorted(os.listdir(srcfilepath))
  start  = int(round(float(len(filelist))/100*int(d)))
  finish = int(round(float(len(filelist))/100*int(e)))
  indexfilepath = localpath+'index_'+str(window)+'_'+str(overlap)+'_'+str(start+1)+'_'+str(finish)+'.txt'
  susp_index = index.fileIndex(susp, window, overlap)
  if os.path.isfile(indexfilepath):
    source_index = writer.fRead(indexfilepath)
    indexflag = False
  else:
    source_index = index.sourceIndex(srcfilepath, window, overlap, start, finish)
    indexflag = True
    #pool.apply_async(writer.fWrite, (indexfilepath, source_index,))
    writer.fWrite(indexfilepath, source_index)
  result = {'index_keys': len(source_index),
            'susp_keys': len(susp_index),
            'window': window,
            'overlap': overlap,
            'merge_distance': mdist,
            'index_from': filelist[start],
            'index_upto': filelist[finish-1],
            'new_index': indexflag}
  result['top_matches'] = match.main(susp_index, source_index, mdist)
  return result

if __name__ == '__main__':
  argvs = sys.argv
  resp = main(argvs[1],argvs[2],argvs[3],argvs[4],argvs[5],argvs[6])
  resp['id'] = argvs[7]
  resp['susp_file'] = argvs[4].split('/')[-1]
  resp['exec_time'] = round(time()-startsec,1)
  '''
  with open(localpath+argvs[7]+'.txt', 'w') as f:
    f.write('ID: ' + resp['id'] + '\n')
    for key, value in resp['result'].items():
      f.write(str(key) + '\t')
      f.write(str(value) + '\t')
      f.write('\n')
  '''
  params = json.dumps(resp)
  headers = {"Content-Type" : "application/json"}
  conn = httplib.HTTPConnection("hand-it-in.appspot.com")
  conn.request("POST", "/", params, headers)
  response = conn.getresponse()
  if response.status == 200:
    conn.close()
  else:
    # log error
    pass
