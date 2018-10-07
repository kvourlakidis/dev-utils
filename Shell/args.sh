#!/bin/bash
echo "Number of args: $#"
if (( $# > 0 ))
then
  echo "Arguments:"
  i=1
  for arg in $@
  do
    echo $i $arg
    i=$((i+1))
  done
else
  echo "No arguments :("
fi
