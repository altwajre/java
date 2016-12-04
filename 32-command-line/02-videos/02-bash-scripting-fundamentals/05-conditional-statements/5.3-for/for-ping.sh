#!/usr/bin/env bash

for i in {200..210}; do ping -c 1 192.168.122.$i >/dev/null && echo 192.168.122.$i is available; done

#$ bash for-ping.sh
# >/dev/null <- ignore the standard output
