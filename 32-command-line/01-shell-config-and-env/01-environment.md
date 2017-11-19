# The Environment

> Environment commands

- printenv - Print part or all of the environment
- set - Set shell options
- export - Export environment to subsequently executed programs
- alias - Create an alias for a command

> Examining the Environment

*environment variables*

```
﻿$ printenv | less

﻿$ printenv USER
william
```

*shell and environment variables*

```
﻿$ set | less
```

*view contents of a single variable*

```
﻿$ echo $HOME
/home/william
```

*alias*

```
﻿$ alias
alias alert='notify-send --urgency=low -i "$([ $? = 0 ] && echo terminal || echo error)" "$(history|tail -n1|sed -e '\''s/^\s*[0-9]\+\s*//;s/[;&|]\s*alert$//'\'')"'
alias egrep='egrep --color=auto'
alias fgrep='fgrep --color=auto'
alias grep='grep --color=auto'
alias l='ls -CF'
alias la='ls -A'
alias ll='ls -alF'
alias ls='ls --color=auto'
```

> Environment Variables

| Variable | Contents  |
|----------|--------------|
| DISPLAY  | The display for graphical environment |
| EDITOR   | Text editing program name |
| SHELL    | shell program name |
| HOME     | home directory pathname |
| LANG     | Defines the character set and collation order of language |
| OLD_PWD  | The previous working directory |
| PAGER    | Paging output program. Often /usr/bin/less |
| PS1      | Prompt String 1. This defines the contents of shell prompt |
| PWD      | The current working directory |
| TERM     | Terminal type name |
| TZ       | Specifies time zone |
| USER     | Your username |

