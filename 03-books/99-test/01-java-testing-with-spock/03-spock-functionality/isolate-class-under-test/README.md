# Isolating the class under test

**stub**
A stub is a fake class that comes with pre-programmed return values.
It's injected in the class under test so that you have absolute control of what's being tested as input.

**mock**
A mock is a fake class that can be examined after the test is finished for its interactions with the class under test.
For example, you can ask it whether a method was called or how many times it was called.
