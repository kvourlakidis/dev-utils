#!/bin/bash

# Ensure we fail if we reference a variable we've not set
set -u

readonly default="troll"
# Check if set
foo=${couldBeNull:=defaultValue}

echo $value