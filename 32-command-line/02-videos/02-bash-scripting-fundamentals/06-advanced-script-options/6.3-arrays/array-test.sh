#!/usr/bin/env bash

names=(Tom Dick Harry Mark)
echo $names # it does not print the whole array, it prints the first element only
echo ${names[@]} # it prints the whole array
ehco ${names[2]} # it prints the index element
echo ${#names[@]} # array size
names[0]=Will
echo ${names[@]} # it prints the whole array
names[4]=Bill # append element to the array
echo ${names[@]} # it prints the whole array

#$ bash array-test.sh
#Tom
#Tom Dick Harry Mark
#array-test.sh: line 6: ehco: command not found
#4
#Will Dick Harry Mark
#Will Dick Harry Mark Bill
