To run an awk program use the -f flag.
Example:
>awk -f this.file [optional_list_of_input_files]

$3 > 0 { print $1, $2 * $3 }

The format of an awk program is:

``` pattern { action } ```


The basic operation of awk is to scan a sequence of input line sone after another,
searching for lines that are matched by any of the patterns in the program.
For each pattern that matches, the corresponding action
(which may involve mulitple steps) is performed.
Then the next line is read and the matching starts over.
This continues until all the input has been read.

Printing every line:
{ print }
{ print $0 }

Printing certain fields:
{ print $1, $3 }

Print the number of fields and last field:
{ print NF, $1, $NF }

Print line numbers:
{ print NR, $0 }

Print static text:
{ print "total pay for", $1, "is", $2 * $3 }

Using printf:
printf(format, value1, value2, ... , value*)

{ printf("total pay for %s is $%.2f\n", $1, $2 * $3) }

{ printf("%-8s $%6.2f\n", $1, $2 * $3) }

Using Unix sort to sort the output:

awk '{ printf("$%6.2f %s\n", $2 * $3, $0) }' emp.data | sort

awk 'END { print NR, "lines" }' emp.data

