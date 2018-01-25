# Functions

https://www.safaribooksonline.com/library/view/clean-code/9780136083238/chapter03.html

Functions are the verbs and classes are the nouns.

## SMALL

Less than 20 lines long

## DO ONE THING

One level of abstraction below the stated name of the function.

Write functions is to decompose a larger concepts into a set of steps at the next level of abstraction.

## ONE LEVEL OF ABSTRACTION PER FUNCTION

Make sure function statements are all at the same level of abstraction.

### Reading Code from Top to Bottom: The Stepdown Rule

Read code like a top-down narrative. We want every function to be followed by those at the next level of abstraction so that we can read the program.

## SWITCH STATEMENTS

It's hard to make a `switch` statement that does one thing because `switch` statements always do N things.

## USE DESCRIPTIVE NAMES

Don’t be afraid to make a name long
Choosing descriptive names will clarify the design of the module in your mind and help to improve it.
Use the same phrases, nouns, and verbs in the function names you choose for your modules.

## FUNCTION ARGUMENTS

The ideal number of arguments for a function is zero (niladic).
Next comes one (monadic).
Followed closely to two (dyadic).
Three arguments (triadic) should be avoided where possible.

### Flag Arguments

Passing a boolean into a function is a bad practice.
It does more than one thing because it does one thing if the flag is true and other if the flag is false!

### Argument Objects

When a function seems to need more than two or three arguments, it is likely that some of those arguments ought to be wrapped into a class of their own.

## HAVE NO SIDE EFFECTS

function promises to do one thing, but it also does other hidden things. Sometimes it will make unexpected changes to the variables of its own class.

## COMMAND QUERY SEPARATION

Functions should either do something or answer something, but not both.
Function should change the state of an object, or it should return some information about that object.

## PREFER EXCEPTIONS TO RETURNING ERROR CODES

Returning error codes from command functions is a subtle violation of command query separation.
It promotes commands being used as expressions in the predicates of `if` statements.

### Extract Try/Catch Blocks

Try/catch blocks are ugly in their own.
They confuse the structure of the code and mix error processing with normal processing.
It is better to extract the bodies of the try and catch blocks out into functions of their own.

### Error Handling Is One Thing

Functions should do one thing. Error handling is one thing.
Try should be the very first word in the function and that there should be nothing after catch/finally blocks.

## DON'T REPEAT YOURSELF

The duplication is a problem because it requires multiple modification when have to make a change.

## STRUCTURED PROGRAMMING

Dijkstra said that every function, and every block within a function, should have one entry an done exit.
There should only one return statement in a function, no break or continue statements in a loop, and never goto statement.
