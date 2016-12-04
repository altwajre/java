#!/usr/bin/env bash

if [ -d $1 ]
then
    echo $1 is a directory
elif [ -f $1 ]
then
    echo $1 is a file
else
    echo $1 is not a file, nor a director
fi

#$ bash ifdir.sh
#is a directory
#$ bash ifdir.sh /etc
#/etc is a directory
#$ bash ifdir.sh /etc/passwd
#/etc/passwd is a file
#$ bash ifdir.sh /etc/paxx
#/etc/paxx is not a file, nor a director
