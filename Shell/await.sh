# file -> /var/opt/mssql/log/errorlog
# String -> "Service Broker manager has started."

# https://superuser.com/questions/270529/monitoring-a-file-until-a-string-is-found

function timeOut() {
    maxAttempts=10
    if ((  $1 >= $maxAttempts )); then
        echo "Timed out, exiting..."
        exit 1
    fi
}

function sqlServerWaitToStart() {
    # startedLine="Service Broker manager has started."
    startedLine="I am a moomin"
    # logFile="/var/opt/mssql/log/errorlog"
    logFile="${HOME}/tmp/moomin.log"
    ctr=0
    until [ -f "${logFile}" ]; do
        timeOut $ctr
        echo "Waiting for ${logFile} (for ${ctr} seconds)"
        sleep 1
        ctr=$((ctr+1))
    done
    echo "File ${logFile} found."
    # grep "${startedLine}" "${logFile}"
    # if [ $? -ne 0 ]; then
    #     tail -f $logFile | while read logLine
    #     do
    #         echo "Waiting for SQL Server manager to start..."
    #         [[ "${logLine}" == *"${startedLine}"* ]] && pkill -P $$ tail
    #     done
    # fi
    ctr=0
    until grep --ignore-case "${startedLine}" "${logFile}"; do
        timeOut $ctr
        echo "Waiting (for ${ctr} seconds)"
        sleep 1
        ctr=$((ctr+1))
    done
    echo "SQL Server manager started."
}

sqlServerWaitToStart