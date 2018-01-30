# Using Live Templates

| Action               | PC shortcut    | Mac shortcut     |
|----------------------|----------------|------------------|
| Select template      | Ctrl + J       | cmd + J          |
| Surrounding template | Ctrl + Alt + J | cmd + option + J |

## All defined postfix code completion templates

Settings | Editor | General | Postfix Completion - `cmd + ,` and search `Postfix Completion`

assert - assert expr
cast - ((SomeType)expr)
field -  myField = expr
for - for (T item : expr)
fori - for (int i = 0; i < expr.length; i++)
format - String.format(expr)
forr - for (int i = expr.length - 1; i >= 0; i--)
inst - expr instanceof Type ? ((Type)expr) : null
instanceof - expr instanceof Type ? ((Type)expr) : null
iter - for (T item : expr)
lambda - () -> expr
nn - if (expr != null)
notnull - if (expr != null)
null - if (expr == null)
opt - Optional.ofNullable(expr)
par - (expr)
return - return expr
sout - System.out.println(expr)
synchronized - synchronized(expr)
try - try { expr } catch (Exception e)
var - T name = expr
