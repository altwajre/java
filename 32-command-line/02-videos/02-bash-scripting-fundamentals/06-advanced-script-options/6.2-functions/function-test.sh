#!/usr/bin/env bash

noarg ()
{
  echo you have not provided an argument
  echo when using this script, you need to specify a filename
  exit 2
}

if [ -z $1 ]; then # -z means true when no arg
  noarg
else
  file $1
fi
exit 0

#$ bash function-test.sh
#you have not provided an argument
#when using this script, you need to specify a filename

#$ bash -x function-test.sh blah <- debug
#+ '[' -z blah ']'
#+ file blah
#blah: cannot open `blah' (No such file or directory)
#+ exit 0

#$ bash function-test.sh /etc/hosts
#/etc/hosts: ASCII English text
