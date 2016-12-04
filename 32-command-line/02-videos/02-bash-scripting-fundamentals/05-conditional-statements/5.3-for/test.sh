#!/usr/bin/env bash

for i in `cat users`; do  echo useradd $i; done

#$ bash test.sh
