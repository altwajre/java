#!/usr/bin/env bash

# script that counts files in a directory

#$ bash count-files.sh
#which directory do you want to count?
#. <- count current directory

#$ bash count-files.sh
#which directory do you want to count?
#/etc

echo which directory do you want to count?
read DIR
cd $DIR
COUNTER=0

for i in * # * means all files in the directory
do
  COUNTER=$((COUNTER+1))
  echo I have counted $COUNTER files in this diretory
done
