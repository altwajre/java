# Java Testing with Spock

Code
https://github.com/kkapelon/java-testing-with-spock

## Run Tests

**Command line**
```
$ gradle test
```

**Test Report**
```
build/reports/tests/index.html
```

## Mock / Stub

**stub**

A fake class with canned responses.
A fake class that can be programmed with custom behavior.

*>>> operator*
```
def "ITest simple Stub()"() {
    given:
    Result pass = new Result(pass: true)
    Result fail = new Result(pass: false)

    ITest test = Stub(ITest)

    // >>> operator
    // first time test.run() is called returns pass 
    // second time test.run() is called returns false
    test.run() >>> [pass, fail]

    when:
    println test.run().pass // first time pass; should see true
    println test.run().pass // second time fail; should see false

    then:
    test
}
```

**mock**

A mock is a fake class that can be examined for its interactions with the class under test.
Faking the input data is only half the effort needed to write effective unit tests.
The other half is faking the output parameters.

The triple-right-shift/unsigned shift (>>>) operator allows a stub to return different results each time it’s called.
