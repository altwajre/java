#!/usr/bin/env bash

#$ bash test.sh
#no argument provided
#$ bash test.sh /etc
#/etc is a directory
#$ bash test.sh /etc/passwd
#/etc/passwd is a file

[ -z $1 ] && echo no argument provided && exit 2 # exit 2 means not successful
[ -f $1 ] && echo $1 is a file && exit 0 # exit 0 means successful
[ -d $1 ] && echo $1 is a directory && exit 0
