#!/bin/bash
# declare an associated array called 'map'
declare -A map
# add key-value pairs
map[cat]=meow
map[dog]=woof
map[wolf]=${map[dog]}
map+=([cow]=moo [hyena]=lol)

# echo ${map[dog]}
# echo ${map[cow]}


if [[ $# == 0 ]]; then
	for k in ${!map[@]}; do
	  echo $k goes ${map[$k]}
	done
else
	for arg in $@; do
		if [[ ${map[$arg]} ]]; then
			echo "$arg goes ${map[$arg]}"
		else
			echo "$arg not in array!"
		fi
	done
fi
