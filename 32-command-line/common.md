# Common

## .bash_profile

> alias

```
$ ls -a
$ cat .bash_profile
alias ot='open -a Terminal "`pwd`"'
alias gla='git log --oneline --all --decorate --graph'
alias g='cd /Users/whan/Desktop/github'
```

## File Commands

> find

$ find . -name 'README.md'

> Remove all *.class files

find . -name "*.class" -print0 | xargs -0 rm -rf

> file permission - executable

```
$ chmod 755 foo.sh
$ chmod +x foo.sh
$ ls -l
total 8
-rwxr-xr-x  1 whan  JOMAX\Domain Users  36 Aug  8 11:21 foo.sh
```

> Delete file `rm file-name`

$ rm .m2/settings.xml

> Copy file1 to file2 `cp file1 file2`

$ cp .m2/settings.xml .m2/settings.xml.ecomm

> Rename source to dest `mv file1 file2`

$ mv .m2/settings.xml .m2/settings.xml.ecomm

## mac command line

https://github.com/0nn0/terminal-mac-cheatsheet/wiki/Terminal-Cheatsheet-for-Mac-(-basics-)

http://ss64.com/osx/  - mac command line
