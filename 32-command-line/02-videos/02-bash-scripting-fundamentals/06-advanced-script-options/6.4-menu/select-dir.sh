#!/usr/bin/env bash

echo "Select a directory: "
select DIR in /bin /usr /etc
do
  # only continue if the user has selected something
  if [ -n $DIR ]
  then
    DIR=$DIR
    echo you have selected $DIR
    export DIR
    break
  else
    echo invalid choice
  fi
done

#$ bash select-dir.sh
#Select a directory:
#1) /bin
#2) /usr
#3) /etc
##? 2
#you have selected /usr
