#!/bin/bash

echo -e "Demonstrates different ways which \
can be used to get hold of the \
current execution directory in \
Bash.\n"
echo "Using \$0: $0"
echo "Using the \$PWD variable: $PWD"
echo "Using the pwd util: $(pwd)"
echo "Using pwd -P: $(pwd -P)"
