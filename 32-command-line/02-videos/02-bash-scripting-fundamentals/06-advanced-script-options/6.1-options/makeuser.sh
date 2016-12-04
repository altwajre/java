#!/usr/bin/env bash

# script that creates users using preferred options
# usage: use -a to add a home directory
#        use -b to make the user member of group 100
#        use -c to specify a custom shell. This option is followed by the shell name

while getopts "abc:" opt # c: means c takes option; opt is variable
do
case $opt in
  a ) VAR1=-m ;;
  b ) VAR2="-g 100";;
  c ) VAR3="-s $OPTARG";; # OPTARG will take the first thing followed by option as option arg, : is telling option to consider OPTARG
  * ) echo 'usage: makeuser [-a] [-b] [-c shell] username' # * means for other cases
esac
done

echo the current arguements are set to $* # $* means all the args have been using.
echo "option index $OPTIND"
echo shift $((OPTIND -1))
shift $((OPTIND -1)) # OPTIND is option index
echo now the current arguments are set to $*
echo useradd $VAR1 $VAR2 $VAR3 $1 # $1 is first arg, not option
exit 0

#$ bash makeuser.sh -a -b tom
#the current arguements are set to -a -b tom
#option index 3
#shift 2
#now the current arguments are set to tom
#useradd -m -g 100 tom

#$ bash makeuser.sh -a -b -c /bin/bash tom
#the current arguements are set to -a -b -c /bin/bash tom
#option index 5
#shift 4
#now the current arguments are set to tom
#useradd -m -g 100 -s /bin/bash tom
