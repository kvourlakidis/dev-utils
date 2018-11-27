#!/bin/bash
# check if file exists
file="test.sh"
if [ -f "$file" ]
then
    echo "$file found."
else
    echo "$file not found."
fi

myFile=STAGING_TABLE_NAMES.txt
myarr=($(awk -F '=' '{print $1}' $myFile))
for i in ${myarr[@]}; do
    echo $i
done
# while read -r line; do
#     TABLE_NAME=awk -F '=' '{print $1}' line
#     echo $TABLE_NAME
# done < $myFile