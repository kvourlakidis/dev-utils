#!/bin/bash

# Control tokens:
# || & && ; ;; ;& ;;& ( ) | |& <newline>
#
# Reserved words:
# ! { } [[ ]]
# if elif else then fi in for while until do done
# case select function
# coproc esac time 
#
# Piping:
# | - connects the stdout of command1 to stdin of command2.
# |& - connects stdout and stderr or command1 to stdin or command2.
# |& is short-hand for 2>&1 |.
# The return status of a pipeline is the exit status of the last command.
# 
# If the 'time' reserved word precdes a pipeline, the elapsed and system
# time consumed by its execution are reported when the pipe terminates.
# The TIMEFORMAT variable may be used to specify the display format.
# time find / -iname $(whoami) | sleep 1 | sleep 1
#
# Command separators:
# ; & && || 
# Command terminators:
# ; & <newline>
# The & terminator puts the command in the background in a subshell.
# The shell does not wait for the command to finish returning 0.
# The ; terminator executes the commands sequentially.
#
# Commands separated by && or || are executed with left associativity:
# && only executes command2 if command1 has exit status 0.
# || only executes command2 if command1 has a non-zero exit status.
#
# Compound commands:
#
# (commands) - executes in a subshell returning the exit status;
# { commands; }* - executes in the current shell.
# ((expression)) - executes an arithmetic expression. If the value of the expression is non-zero, the return status is 0; otherwise the return status is 1.
# [[ expression ]] - evaluates a conditional expression.
#
# *- note that unlike ( and ), { and } are reserved words and must be
# separated by whitespace.
# 
# Equality testing:
# The == and != operators are used to test string pattern matching.
# The = operator is equivalent to ==.
# The 'nocasematch' option can be used to control case-sensitivity.
# The return value is 0 if the string matches and 1 otherwise.
#
# Loops and control structures:
# for name [ [ in [ word ... ] ] ; ] do list ; done
#
# for (( expr1; expr2; expr3; )); do <commands>; done
#
# select name [ in word ] ; do <commands>; done
#
# case word in [ [(] pattern ) list ;; ] ... esac
#
# if list; then list; [ elif list; then list; ] ... [ else list; ] fi
#
# while list1; do list2; done
# until list1; do list2; done
#
# Coprocess - a shell command preceded by the 'coproc' keyword is executed
# asynchronously in a subshell, as if the command was terminated with the
# '&' control character, with a two-way pipe established betwween the 
# executing shell and the coprocess. The format for a coprocess is:
# coproc [NAME] command [redirections]
#
# Function definition:
# name () compound-command [redirection]
# function name [()] compound-command [redirection]
#
# Escape characters (backslash, single and double quotes):
# \ - preserves the literal value of the next character. Also used to escape newlines (\<newline> is treated as a line continuation).
# ' - preserves the literal value of each character within the quotes.
# " - preserves the literal value of all characters witin the quotes, with the exception of $, `, \ (and ! when history expansion is enabled).
# The special parameters * and @ have special meaning when in double quotes.
# 
# Variable assignment:
# name=[value]
# myStringVar+="value to append"
# myIntVar+=10
# myListArray+="next value"
# myAssociativeArray+=([key]:value)
#
# If value is not given, the variable is assigned the null string.
# 
# Special parameters:
# * positional parameters starting from one. Separated by IFS.
# @ positional parameters starting from one. Separated by spaces.
# # the number of positional parameters.
# ? the exit status of the most recently executed foreground pipeline.
# - the current option flags specified upon invocation.
# $ the process ID of the shell.
# ! the process ID of the job most recently placed in the background.
# 0 expands to the name of the shell or shell script.
# _ the absolute pathname used to invoke the shell.
#
# Arrays

