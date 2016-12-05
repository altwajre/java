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

#$ bash -x bash-x-test.sh blah <- debug
#+ '[' -z blah ']'
#+ file blah
#blah: cannot open `blah' (No such file or directory)
#+ exit 0
