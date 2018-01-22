#!/usr/bin/env python

import cgi
import cgitb
import os
import subprocess
import json
import random

cgitb.enable() # enable html based error reporting for cgi

localpath = '/home/ec2-user/Documents/'
#localpath = '/home/kirill/Documents/COMM034/'
#jobID = 'id-%06d' % random.randint(0,100000)
jobID = random.randint(10000,99999)

try:
  subprocess.check_output(["cat", "/tmp/msg"])
  form = cgi.FieldStorage() # get query payload
  window = int(form.getvalue('w'))
  overlap = int(form.getvalue('o'))
  mdist = int(form.getvalue('m'))
  if form.getvalue('s'):
    start = int(form.getvalue('s'))
  else: start = 0
  if form.getvalue('f'):
    finish = int(form.getvalue('f'))
  else: finish = 100
  a = int(filter(str.isdigit, form.getvalue('file')))
  suspfile = 'suspicious-document%0.5d.txt' % a
  suspfilepath = localpath + 'susp-text-PAN11/' + suspfile
  if not os.path.isfile(suspfilepath):
    raise ValueError('Suspicious file name value error')
  response = {'jobID': jobID,
             'window': window,
             'overlap': overlap,
             'mergedistance': mdist,
             'suspicious file': suspfile,
             'index start': start,
             'index finish': finish
             }
  cmd = ["python",
         "responder.py",
         str(window),
         str(overlap),
         str(mdist),
         suspfilepath,
         str(start),
         str(finish),
         str(jobID)]
  subprocess.Popen(cmd, stdout = subprocess.PIPE, stderr = subprocess.STDOUT)
  print "Content-Type: application/json\n"
  print json.dumps(response)
# error handling
except ValueError as e:
  print "Content-Type: text/plain \n\n"
  print e
  print "Suspicious file (%s) not found" % suspfile
except Exception as e:
  print "Content-Type: text/html \n\n"
  print "<html><head><title>Error response from EC2 server.</title></head><body><p>"
  print "Something went wrong:<br>"
  print e
  #print e.message, e.args
  print "</p></body></html>"
