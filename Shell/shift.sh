#!/bin/bash

echo "Number of args: $#"
echo "\$1 = $1"
echo "\$2 = $2"
echo "\$3 = $3"

echo "shift"
shift
echo "\$1 = $1"
echo "\$2 = $2"
echo "\$3 = $3"

echo "shift 2"
shift 2
echo "\$1 = $1"
echo "\$2 = $2"
echo "\$3 = $3"
