#!/bin/bash
#
declare -A urls
urls["local"]="http://localhost:3000"
urls["home"]="https://news.ycombinator.com/news"
#
if (( $# == 1 ))
then
  if [[ ${urls[$1]} ]]
  then
    echo $1 : ${urls[$1]}
  else
    echo "$1 - option not found"
  fi
else
  echo "wrong number of arguments"
fi
