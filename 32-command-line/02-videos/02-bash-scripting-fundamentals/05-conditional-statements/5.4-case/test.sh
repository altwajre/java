#!/usr/bin/env bash

#$ bash test.sh huh
#try again
#$ bash test.sh yes
#ok
#$ bash test.sh no
#too bad

VAR=$1

case $VAR in
yes) # when VAR is yes
  echo ok;;
no|nee) # when VAR is no or nee
  echo too bad;;
*) # default
  echo try again;;
esac
