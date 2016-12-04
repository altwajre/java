#!/usr/bin/env bash

COUNTER=0

while true
do
  sleep 1
  COUNTER=$((COUNTER+1))
  echo $COUNTER seconds have passed since starting this script
done

#$ bash while-test.sh
#ctrl+c to stop it
