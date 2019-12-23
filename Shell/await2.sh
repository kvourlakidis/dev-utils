#!/bin/bash

function longRunningOp() {
    numOfSeconds=$(( ( RANDOM % 10 ) + 1 ))
    echo "Sleeping $numOfSeconds seconds..."
    sleep $numOfSeconds
    if (( numOfSeconds % 2 == 0 )); then
        return 0;
    else
        return 1;
    fi
}

logFile="my.file"
until [ -f "${logFile}" ]; do
    echo "Waiting for ${logFile}..."
    sleep 1
done