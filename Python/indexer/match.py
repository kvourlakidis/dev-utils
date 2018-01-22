#!/usr/bin/env python

from math import sqrt

def sortKey1(i):
  return i[0],i[1]

def main(suspindex, sourceindex, mergedist):
  match_list = []
  for key in suspindex:
    if key in sourceindex:
      a = len(sourceindex[key])
      for i in range(0,a,3):
        match_list.append([sourceindex[key][i],int(sourceindex[key][i+1]),int(sourceindex[key][i+2])])

  match_list = sorted(match_list, key=sortKey1)

  i = 0
  while i < len(match_list)-1:
    if match_list[i] == match_list[i+1]:
      del match_list[i]
    elif match_list[i+1][1] <= (match_list[i][2] + mergedist) and match_list[i+1][0] == match_list[i][0]:
      match_list[i][2] = match_list[i+1][2]
      del match_list[i+1]
    else:
      i = i + 1

  matchdict = {}
  for line in match_list:
    if line[0] in matchdict:
      matchdict[line[0]] += (line[2]-line[1])*(line[2]-line[1])
    else:
      matchdict[line[0]] = (line[2]-line[1])*(line[2]-line[1])

  topfive = {}
  for key in sorted(matchdict, key=matchdict.__getitem__, reverse=True)[:5]:
    topfive[key] = int(sqrt(matchdict[key]))

  return topfive
