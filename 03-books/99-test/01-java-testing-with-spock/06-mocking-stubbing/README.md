# Mocking and stubbing

**Stub**
A stub is a fake class that comes with preprogrammed return values.
It's injected into the class under test so that you have absolute control over that's being tested as input.

**Mock**
A mock is a fake class that can be examined after the test is finished for its interactions with the class under test.
The fake reactor class is a mock so that you can ask whether its shutdown() method was called after test is finished.
