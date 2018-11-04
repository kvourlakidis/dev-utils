#!/bin/bash

# Control tokens:
	# || & && ; ;; ;& ;;& ( ) | |& <newline>

# Reserved words:
	# ! { } [[ ]]
	# if elif else then fi in for while until do done
	# case select function
	# coproc esac time 

# Piping:
	# | - connects the stdout of command1 to stdin of command2.
	# |& - connects stdout and stderr or command1 to stdin or command2.
	# |& is short-hand for 2>&1 |.
	# The return status of a pipeline is the exit status of the last command.

# Timing:
	# If the 'time' reserved word precedes a pipeline,
	# the elapsed and system time consumed by its execution
	# are reported when the pipe terminates.
	# The TIMEFORMAT variable may be used to specify
	# the display format.
	# time find / -iname $(whoami) | sleep 1 | sleep 1

# Command separators:
	# ; & && || 
# Command terminators:
	# ; & <newline>

	# The & terminator puts the command in the background in a subshell.
	# The shell does not wait for the command to finish returning 0.
	# The ; terminator executes the commands sequentially.

# Commands separated by && or || are executed with left associativity:
	# && only executes command2 if command1 has exit status 0.
	# || only executes command2 if command1 has a non-zero exit status.

# Compound commands:
	# (commands) - executes in a subshell returning the exit status;
	# { commands; }* - executes in the current shell.
	# ((expression)) - executes an arithmetic expression. If the value of the expression is non-zero, the return status is 0; otherwise the return status is 1.
	# [[ expression ]] - evaluates a conditional expression.

	# *- note that unlike ( and ), { and } are reserved words and must be separated by whitespace.

# Equality testing:
	# The == and != operators are used to test string pattern matching.
	# The = operator is equivalent to ==.
	# The 'nocasematch' option can be used to control case-sensitivity.
	# The return value is 0 if the string matches and 1 otherwise.

# Loops and control structures:
	# for name [ [ in [ word ... ] ] ; ] do list ; done

	# for (( expr1; expr2; expr3; )); do <commands>; done

	# select name [ in word ] ; do <commands>; done

	# case word in [ [(] pattern ) list ;; ] ... esac

	# if list; then list; [ elif list; then list; ] ... [ else list; ] fi

	# while list1; do list2; done
	# until list1; do list2; done

# Coprocess
	# A shell command preceded by the 'coproc' keyword is executed
	# asynchronously in a subshell, as if the command was terminated
	# with the '&' control character, with a two-way pipe
	# established betwween the executing shell and the coprocess.
	# The format for a coprocess is:
	# coproc [NAME] command [redirections]

# Function definition:
	# name () compound-command [redirection]
	# function name [()] compound-command [redirection]

# Escape characters (backslash, single and double quotes):
	# \ - preserves the literal value of the next character.
	# Also used to escape newlines (\<newline> is treated as a line continuation).
	# ' - preserves the literal value of each character within the quotes.
	# " - preserves the literal value of all characters witin the quotes, with the exception of $, `, \ (and ! when history expansion is enabled).
	# The special parameters * and @ have special meaning when in double quotes.
 
# Variable assignment:
	# name=[value]
	# myStringVar+="value to append"
	# myIntVar+=10
	# myListArray+="next value"
	# myAssociativeArray+=([key]:value)
	# If value is not given, the variable is assigned the null string.

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

# Arrays

	# Indexed arrays
	# name[number]=value
	# declare -a name
	# declare -a name[subscript]
	# name=(value1 ... valuen)

	# Associative arrays
	# declare -A name

	# ${name[subscript]}
	# If subscript is @ or * the word expands to all members of name.
	# ${!name[@]} and ${!name[@]} expand to the indices.

	# The 'unset' builtin is used to destroy arraus.
	# unset myArray or unset myArray[* or @] removes the entire array.

# Brace expansion:
echo a{b,c,b}e # ade ace abe.
echo {one-{one,two},two-{two,three}} # one-one one-two two-two two-three

# Tilde expansion:
	# ~/ expands to $HOME/
	# ~name/ if name is a valid login name expands to home for that name
	# ~+/myDir expands to $PWD/myDir
	# ~-/myDir expands to $OLDPWD/myDir if OLDPWD is set.

# Parameter expansion:
	# ${parameter:-word} - use default values.
	# ${parameter:=word} - assign default values.
	# ${parameter:?word} - display error or null or unset.
	# ${parameter:+word} - use alternatve value.
	# ${parameter:offset}
	# ${parameter:offset:length} - substring expansion.
	# ${!prefix*}
	# ${!prefix@} - names matching prefix.
	# ${!name[@]}
	# ${!name[*]} - list of arrays.
	# ${#parameter} - parameter length.
	# ${parameter#word}
	# ${parameter##word} - remove matching prefix pattern.
	# ${parameter%word}
	# ${parameter%%word} - remove matching suffix pattern.
	# ${parameter/pattern/string} - pattern substitution.
	# ${parameter^pattern}
	# ${parameter^^pattern}
	# ${parameter,pattern}
	# ${parameter,,pattern} - case modification.
	# ${parameter@operator} - parameter transformation.

# Command substitution:
	# Allows the output of a command to replace the command name.
	# $(command) - characters that make up command are not treated specially.
	# `command` - backslash escapes: \$, \`, \\.
	# $(< file) is equivalent and preferable to $(cat file).

# Arithmetic expression
	# $((expression))
	# $[expression] - deprecated form.

# Process substitution
	# Process subsitution allows a process's input or output to be
	# referred to using a filename.
	# <(list) - output of list is written to the file.
	# >(list) - writing to the file provides input for list.

# Pattern matching
	# * matches any string
	# ? matches any character
	# [abcd] matches any one of the enclosed characters.
	# [a-z] matches character in the range using the current collating
	# sequence. Controlled via the LC_COLLATE or LC_ALL variables.
	# If the first character after the [ is ! or ^, negates the match.
	# [:class:] matches a POSIX character class:
	# alnum alpha ascii cntrl digit graph lower print punct
	# space upper word xdigit
	# The extglob shell option enables composite pattern matching.

# Redirection
	# Redirecting input
	# [n]<word - causes 'word' to be opened for reading on
	# file descriptor n or the standard input (0) if omitted.
	
	# Redirecting output
	# [n]>word - causes 'word' to be opened to writing on
	# file descriptor n or the standard output (1) if omitted.


