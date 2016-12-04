#!/usr/bin/env bash

#$ bash test.sh

for i in `cat users`; do  echo useradd $i; done
