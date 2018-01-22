#!/bin/bash
declare -A map
map[cat]=meow
map[dog]=woof
map[wolf]=${map[dog]}
map+=([cow]=moo [hyena]=lol)
# echo ${map[dog]}
# echo ${map[cow]}
for k in ${!map[@]}
do
  echo $k goes ${map[$k]}
done
for arg in $@
do
  if [[ ${map[$arg]} ]]
  then
    echo ${map[$arg]}
  else
    echo "$arg not in array!"
  fi
done
