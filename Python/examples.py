#!/usr/bin/env python

import sys
import os

# method definition
def main(args):
    if args:
        print 'Args:'
        for arg in args:
            print arg
            if arg in globals():
                toCall = globals()[arg]
                if callable(toCall):
                    toCall()
    else:
        print 'Args are empty.'


def printStdin():
    if sys.stdin.isatty():
        print 'sys.stdin is a tty'
    else:
        print 'sys.stdin is not a tty'
        for line in sys.stdin:
            print line

def readFileContents(filename):
    with open(filename,'r') as f:
        print 'Reading from: \n' + f.name
        contents = f.read()
        return contents

def writeFileContents(filename, contents):
    with open(filename,'w') as f:
        realpath = os.path.realpath(f.name)
        print 'Writing to: \n' + realpath + '\n\n'
        f.write(contents)

def rangeloop():
    for i in range(0,10):
        print i

def listloop():
    mylist = ['a', 'b', 'c', 'd']
    for element in mylist:
        print element

def loopWithIndex():
    mylist = ['a', 'b', 'c', 'd']
    for idx, element in enumerate(myList):
        print idx, element

def loopControl():
    for i in range(0,10):
        if (i < 5):
            continue
        else:
            print i
            break

def stringjoin():
    strings = ["python", "is", "cool"]
    assert "-".join(strings) == "python-is-cool"

def stringsplit():
    s = "string to split"
    assert s.split() == ["string", "to", "split"]

def listindexes():
    l = ["a", "b", "c", "d"]
    assert l[0] == "a"
    assert l[-1] == "d"

def length():
    assert len("12345") == 5

def stringFunctions():
    assert "string".startswith("s")
    assert "string".endswith("g")
    assert "IF" in "life".upper()
    assert "life" == "LIFE".lower()
    assert "foobar".index("bar") == 3
    assert "  foo ".strip() == "foo"
    assert "__ foo_ ".strip("_") == " foo_"
    assert "__ foo_ ".strip("_ ") == "foo"
    assert "f" * 5 == "fffff"

def stringJoin():
    sa = ["f", "o", "o"]
    print "_".join(sa) # prints f_o_o


# determine if this is the entry point
if (__name__ == "__main__"):
    assert sys.argv[0] == 'examples.py'
    main(sys.argv[1:])

