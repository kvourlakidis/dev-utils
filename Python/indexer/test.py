 #!/usr/bin/env python

import sys
import httplib
import json

print __name__

if __name__ == "__main__":
  for arg in sys.argv:
    print arg

  a = {'ID':'id-010', 'content': {'doc1':10, 'doc2':20, 'doc3':30}}
  params = json.dumps(a)
  headers = {"Content-Type" : "application/json"}
  conn = httplib.HTTPConnection("hand-it-in.appspot.com") 
  conn.request("POST", "/", params, headers)
  response = conn.getresponse()
  print response.status, type(response.status)
  data = response.read()
  print data
  conn.close()
