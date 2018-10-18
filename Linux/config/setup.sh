#!/bin/bash

# fix the bashrc
BASHRCEXT="$(pwd)/bashrcWRONG"
ls $BASHRCEXT 2>/dev/null # 1&2>/dev/null
if (( $? == 0 )); then
	echo "file exists"
else
	echo "bashrc extension file missing"
fi
# fix the swappiness

# fix the cache pressure setting
