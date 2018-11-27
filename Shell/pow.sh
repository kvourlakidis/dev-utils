#!/bin/bash
f=moomin.txt
text=$(<$f)
diff=00
token=0
done=0
while [ $done -eq 0 ]; do
	hash=$(echo "$text$token" | shasum -a 256)
	# echo $hash
	if [[ $hash == $diff* ]]; then
		echo "$text$token" > $f	
        echo "done"
		done=1
	fi
	((token++))
done
