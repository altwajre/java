# MAC

> list listening ports

lsof -Pn -i4 | grep LISTEN

> clear up command line

`ctrl + u` or `ctrl + c`

> `~/.bash_profile`

> cat ~/.bash_profile

```
alias ot='open -a Terminal "`pwd`"'
alias gla='git log --oneline --all --decorate --graph'
alias gg='cd /Users/whan/git/gd'
alias g='cd /Users/whan/git/public'

export JAVA_HOME=$(/usr/libexec/java_home)
```

> redirection

- append
echo hello >> /var/log/yum.log

## Reset the System Management Controller (SMC)

https://support.apple.com/en-us/HT201295
