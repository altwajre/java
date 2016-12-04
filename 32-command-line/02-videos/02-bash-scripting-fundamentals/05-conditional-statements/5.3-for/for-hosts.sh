#!/usr/bin/env bash

#$ bash for-hosts.sh

for i in `cat /etc/hosts`; do echo $i; done
