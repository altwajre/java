# Documentation

Oracle Java Documentation

https://docs.oracle.com/javase/tutorial/essential/regex/index.html

## Metacharacters
<([{\^-=$!|]})?*+.>

## Capturing Groups
Capturing groups are a way to treat multiple characters as a single unit. 
They are created by placing the characters to be grouped inside a set of parentheses. 
For example, the regular expression (dog) creates a single group containing the letters `d` `o` and `g`.
The portion of input string matches capturing group will be saved in memory for later recall via backreferences.

### Backreferences
A backreference is specified in regular expression as a backslash (\) followed by a digit indicating the number of the group to be recalled.
To match any 2 digits, followed by the exact same two digits, you would use (\d\d)\1 as the regular expression.

## Character Classes
| Construct      | Description                                                      |
|----------------|------------------------------------------------------------------|
| [abc]          | `a`, `b` or `c` (simple class)                                   |
| [^abc]         | Any character except `a`, `b`, or `c` (negation)                 |
| [a-zA-Z]       | `a` through `z`, or `A` through `Z`, inclusive (range)           |
| [a-d[m-p]]     | `a` through `d`, or `m` through `p`: [a-dm-p] (union)            |
| [a-z&&[def]]   | `d`, `e`, or `f` (intersection)                                  |
| [a-z&&[^bc]]   | `a` through `z`, except for `b` and `c`: [ad-z] (subtraction)    |
| [a-z&&[^m-p]]  | `a` through `z`, and not `m` through `p`: [a-lq-z] (subtraction) |

## Predefined Character Classes
| Construct | Description                                           |
|-----------|-------------------------------------------------------|
|.          | Any character (may or may not match line terminators) |
|\d         | A digit: [0-9]                                        |
|\D         | A non-digit: [^0-9]                                   |
|\s         | A whitespace character: [ \t\n\x0B\f\r]               |
|\S         | A non-whitespace character: [^\s]                     |
|\w         | A word character: [a-zA-Z_0-9]                        |
|\W         | A non-word character: [^\w]                           |

## Quantifiers
| Greedy | Reluctant | Possessive | Meaning                                      |
|--------|-----------|------------|----------------------------------------------|
|X?      |X??        |X?+         |X, ? once or not at all                       |
|X*      |X*?        |X*+         |X, * zero or more times                       |
|X+      |X+?        |X++         |X, + one or more times                        |
|X{n}    |X{n}?      |X{n}+       |X, {n} exactly n times                        |
|X{n,}   |X{n,}?     |X{n,}+      |X, {n,} at least n times                      |
|X{n,m}  |X{n,m}?    |X{n,m}+     |X, {n,m} at least n but not more than m times |

## Boundary Matchers
| Boundary Construct | Description                                               |
|--------------------|-----------------------------------------------------------|
|^                   | The beginning of a line                                   |
|$                   | The end of a line                                         |
|\b                  | A word boundary                                           |
|\B                  | A non-word boundary                                       |
|\A                  | The beginning of the input                                |
|\G                  | The end of the previous match                             |
|\Z                  | The end of the input but for the final terminator, if any |
|\z                  | The end of the input                                      |

