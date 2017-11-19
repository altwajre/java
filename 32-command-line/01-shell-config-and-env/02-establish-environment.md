# How Is the Environment Established?

When we log on to the system, the bash program starts and reads a series of configuration scripts called startup files,
which define the default environment shared by all users.

## Login and Non-login Shells

> login shell session

prompt for username and password

*Startup Files for Login Shell Sessions*

| File            | Contents  |
|-----------------|--------------|
| /etc/profile    | A global configuration script that applies to all users |
| ~/.bash_profile | A user's personal startup file. Can extend or override global configuration script settings |
| ~/.bash_login   | If ~/.bash_profile is not found, bash attempts to read this script |
| ~/.profile      | If neither ~/.bash_profile nor ~/.bash_login is found, bash attempts to read this file |

> non-login shell session

*Startup Files for Non-Login Shell Sessions*

| File             | Contents  |
| /etc/bash.bashrc | A global configuration script that applies to all users |
| ~/.bashrc        | A user's personal startup file. Extend or override global config settings |

## What's in a Startup File?

> `~/.bash_profile` - centos

```
$ cat ~/.bash_profile
# .bash_profile

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
	. ~/.bashrc
fi

# User specific environment and startup programs

PATH=$PATH:$HOME/.local/bin:$HOME/bin

export PATH
```

- `#` - comments
- if compound command

```
if [ -f ~/.bashrc ]; then
	. ~/.bashrc
fi

means

If the file "˜/.bashrc" exists, then
        read the "˜/.bashrc" file.
```

> `PATH` - shell finds commands via `PATH` variable

> `/etc/profile`

```
PATH=$PATH:$HOME/bin
```

*append text*

```
$ foo="This is some"
$ echo $foo
This is some
$ foo=$foo" text."
$ echo $foo
This is some text.
```

> `export PATH`

|------------------|--------------|
The export command tells the shell to make the contents of PATH available to child processes of this shell

