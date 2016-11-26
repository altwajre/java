#!/usr/bin/env bash
# $ bash move_files.sh <- run the bash script
# delete example folder after it is done

echo "press enter to continue:"
read enter

echo "setup directory and show contents:"
rm -rf example
mkdir example
cd example
touch chapter{1..9}
ls
echo ""
echo "press enter to continue:"
read enter

echo "show date in pretty format:"
date +%Y%m%d
echo ""
echo "rename files in current directory with date extention"
for file in $(ls); do mv $file ${file}-$(date +%Y%m%d); done

echo ""
echo "display final name:"
ls
