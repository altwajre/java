#!/usr/bin/env bash

# administration menu

echo "select a task: "
select TASK in 'Check mounts' 'Check disk space' 'Check Memory usage'
do
  case $REPLY in
    1) TASK=mount;;
    2) TASK="df -h";;
    3) TASK="free -m";;
    *) echo ERROR && exit 2;;
  esac
  if [ -n "$TASK" ]
  then
    clear
    $TASK
    break
  else
    echo INVALID CHOICE && exit 3
  fi
done

#$ bash select-task.sh
#select a task:
#1) Check mounts
#2) Check disk space
#3) Check Memory usage
##? 2
