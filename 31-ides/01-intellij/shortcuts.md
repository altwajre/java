# Shortcuts

psvm - generate main()

https://www.jetbrains.com/help/idea/mastering-intellij-idea-keyboard-shortcuts.html

⌘ - Command
⌥ - Alt
⇧ - Shift
⌃ - Ctrl

## Remember these Shortcuts

| Action                                 | Mac shortcut                     | youtube                                     |
|----------------------------------------|----------------------------------|---------------------------------------------|
| Smart code completion                  | Ctrl + Alt + Space               | https://www.youtube.com/watch?v=SeUG1Ia5eJ8 |
| Search everywhere                      | Double Shift                     |   |
| Show intention actions and quick-fixes | Alt + Enter                      |   |
| Generate Code                          | Command + N                      |   |
| Parameter info                         | Command + P                      |   |
| Move up and down                       | Shift + Command + Up/Down arrows |   |
| Extend/Shrink selection                | Alt + Up/Down arrows             |   |
| Recent files popup                     | Command + E                      |   |
| Rename                                 | Shift + F6                       |   |
| Expand/Collapse all functions          | Shift + Command + +/-            |   |


## Using Live Templates

| Action               | PC shortcut    | Mac shortcut     |
|----------------------|----------------|------------------|
| Select template      | Ctrl + J       | cmd + J          |
| Surrounding template | Ctrl + Alt + J | cmd + option + J |

## All defined postfix code completion templates

Settings | Editor | General | Postfix Completion - `cmd + ,` and search `Postfix Completion`

```
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
```

## Search Everywhere

| Action            | PC shortcut       | Mac shortcut      |
|-------------------|-------------------|-------------------|
| Search Everywhere | Press Shift twice | Press Shift twice |

## Navigating between files

| Action                       | PC shortcut           | Mac shortcut    |
|------------------------------|-----------------------|-----------------|
| View recent files            | Ctrl + E              | cmd + E         |
| View recently modified files | Ctrl + Shift + E      | cmd + Shift + E |
| Navigate to a file           | Ctrl + Shift + N      | cmd + Shift + O |
| Navigate to a class          | Ctrl + N              | cmd + O         |
| Navigate to a symbol         | Ctrl + Alt + Shift + N| cmd + Alt + O   |
| Navigate to declaration      | Ctrl + B              | cmd + B         |
| Navigate to super            | Ctrl + U              | cmd + U         |
| Navigate to implementations  | Ctrl + Alt + B        | cmd + Alt + B   |
| Navigate to a test           | Ctrl + Shift + T      | cmd + Shift + T |
| Place a bookmark             | F11                   | F3              |
| Show bookmarks               | Shift + F11           | cmd + F3        |

## Navigating within a single file

| Action                         | PC shortcut | Mac shortcut |
|--------------------------------|-------------|--------------|
| Go to a line                   | Ctrl + G    | cmd + L      |
| Check the file structure       | Ctrl + F12  | cmd + F12    |
| Navigate to next error/warning | F2          | F2           |
| Navigate to prev error/warning | Shift + F2  | Shift + F2   |

## Editor basics

| Action                   | PC shortcut | Mac shortcut |
|--------------------------|-------------|--------------|
| Duplicate a line of code | Ctrl + D    | cmd + D      |
| Comment portions of code | Ctrl + /    | cmd + /      |

## Searching and replacing

| Action       | PC shortcut      | Mac shortcut    |
|--------------|------------------|-----------------|
| Text search  | Ctrl + F         | cmd + F         |
| Search next  | F3               | cmd + G         |
| Search press | Shift + F3       | cmd + shift + G |
| Find in path | Ctrl + Shift + F | cmd + shift + F |
| Replace      | Ctrl + R         | cmd + R         |

## Clipboard history

| Action                       | PC shortcut       | Mac shortcut      |
|------------------------------|-------------------|-------------------|
| Paste prev clipboard content | Ctrl + Shift + V  | cmd + shift + V   |

## Reformatting code

| Action            | PC shortcut    | Mac shortcut     |
|-------------------|----------------|------------------|
| Formatting code   | Ctrl + Alt + L | cmd + option + L |
| Line indentations | Ctrl + Alt + I | ctrl + alt + I   |
| Organize imports  | Ctrl + Alt + O | ctrl + alt + O   |

## Code completion

| Action          | PC shortcut          | Mac shortcut       |
|-----------------|----------------------|--------------------|
| Show more items | Ctrl + Shift + Space | ctrl + alt + space |

## Generating code

| Action        | PC shortcut  | Mac shortcut |
|---------------|--------------|--------------|
| Generate code | Alt + Insert | cmd + N      |

## Comparing files and folders

### Mac

View, Compare With ...

F7 - move to next

Shift - F7 - move to prev
