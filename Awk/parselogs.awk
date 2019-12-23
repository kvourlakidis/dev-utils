BEGIN {
    print "date,time,batches processed,rows processed,database duration,indexing duration,total elapsed"
}
{ 
    if ($8 == "Batches" && $9 == "processed:") {
        print $1 ",\"" $2 "\"," substr($10, 1, length($10)-1) ",\"" substr($14, 1, length($14)-1) "\",\"" $17 "\",\"" $21 "\",\"" $25 "\""
    }
}