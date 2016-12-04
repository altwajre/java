#!/usr/bin/env bash

until users | grep $1 > /dev/null
do
  echo $1 is not logged in yet
  sleep 5
done

echo $1 has just logged in
mail -s "$1 as just logged in" root < .
