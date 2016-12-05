#!/usr/bin/env bash

function erroneous
{
  echo the error is $error
}

trap DEBUG

echo this line is OK
erroneous

trap - DEBUG

echo this line is also good

#$ bash debug-trap.sh
#this line is OK
#the error is
#this line is also good
