#!/usr/bin/env bash
PIDFile="./bin/app.pid"
function check_if_pid_file_exists {
    if [ ! -f $PIDFile ]
    then
        echo "PID file not found: $PIDFile"
        exit 1
    fi
}
function check_if_process_is_running {
    if ps -p $1 > /dev/null
    then
        return 0
    else
        return 1
    fi
}

check_if_pid_file_exists
pid=$(cat ${PIDFile})
if ! `check_if_process_is_running ${pid}`
    then
        echo "process ${pid} already stopped"
    exit 0
else
    echo "killing ${pid}"
    kill ${pid}
fi

LOOPS=0
while (true);
do
    if ! `check_if_process_is_running ${pid}`
    then
    	echo "Oook! cost:$LOOPS"
    	break;
    fi
    let LOOPS=LOOPS+1
    sleep 1s
done