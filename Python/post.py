#!/usr/bin/env python

import urllib2
import urllib

url = "http://watson-interview.mybluemix.net/botchat"

values = { 'msgdata': 'blahblahblah' }

data = urllib.urlencode(values)

#print data

req = urllib2.Request(url, data)
#req = urllib2.Request(url)
print "sending request to: " + url
print req
response = urllib2.urlopen(req)
print "getting response"
html = response.read()
print "reading response"
print html