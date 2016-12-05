#!/usr/bin/env bash

# centos - run it in centos
# Monitor CPU Utilization

INTERVAL=$1 # $1 means command line first arg

while sleep $INTERVAL
do
  VALUE1=$(ps -eo pcpu,pid -o comm= | sort -k1 -n -r | head -1)

  USAGE1=$(echo $VALUE1 | awk '{ print $1 }')
  UAGEE1=${USAGE1%.*}
  PID1=$(echo $VALUE1 | awk '{ print $2 }')
  PNAME1=$(echo $VALUE1 | awk '{ print $3 }')

  if [ $UAGEE1 -gt 80 ]
  then
    sleep 7
    VALUE2=$(ps -eo pcpu,pid -o comm= | sort -k1 -n -r | head -1)
    USAGE2=$(echo $VALUE2 | awk '{ print $1 }')
    UAGEE2=${USAGE2%.*}
    PID2=$(echo $VALUE2 | awk '{ print $2 }')
    PNAME2=$(echo $VALUE2 | awk '{ print $3 }')
    [ $USAGE2 -gt 80 ] && [ $PID1 = $PID2 ] && \
      mail -s "CPU load of $PNAME1 is above 80%" root < .
  fi
done

#$ while true; do true; done & <- create an infinite loop job that uses lots of cpu
#$ ps -eo pcpu,pid -o comm= | sort -k1 -n -r | head -1 <- list the highest cpu usage process
#99.9  84937 bash

#$ bash monitor-cpu.sh 3 <- run the monitor cpu bash script with sleep 3; it will send an email to room
#Null message body; hope that's ok
#^C

#[root@osboxes ~]# mail <- validate the mail of root
#Heirloom Mail version 12.5 7/5/10.  Type ? for help.
#"/var/spool/mail/root": 1 message 1 new
#>N  1 osboxes.org           Mon Dec  5 19:12  18/642   "CPU load of bash is above 80%"
#&
#Message  1:
#From osboxes@osboxes.localdomain  Mon Dec  5 19:12:34 2016
#Return-Path: <osboxes@osboxes.localdomain>
#X-Original-To: root
#Delivered-To: root@osboxes.localdomain
#Date: Mon, 05 Dec 2016 19:12:34 +0000
#To: root@osboxes.localdomain
#Subject: CPU load of bash is above 80%
#User-Agent: Heirloom mailx 12.5 7/5/10
#Content-Type: text/plain; charset=us-ascii
#From: osboxes@osboxes.localdomain (osboxes.org)
#Status: R
#&
#At EOF

#$ ps -eo pcpu,pid -o comm= | sort -k1 -n -r | head -1 <- list the highest cpu usage process
#99.9  84937 bash
#$ kill 84937 <- kill the highest cpu usage process
#[2]-  Terminated              while true; do
#    true;
#done
