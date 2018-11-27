#!/usr/bin/env python

from hashlib import sha256 as hashfunction

fileIn="moomin.txt"
fileOut="out.txt"
difficulty="000000"

with open(fileIn, "r") as fIn:
    text = fIn.read()
    i = 0
    done = False
    while (not done):
        newtext = text + str(i)
        h = hashfunction(newtext).hexdigest()
        if (h.startswith(difficulty)):
            done = True
        i = i + 1
    with open(fileOut, "w") as fOut:
        fOut.write(newtext)