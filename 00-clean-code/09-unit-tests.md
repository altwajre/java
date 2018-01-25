# Unit Tests

https://www.safaribooksonline.com/library/view/clean-code/9780136083238/chapter09.html

Fast
Independent
Repeatable
Self-Validating
Timely

## THE THREE LAWS OF TDD

First Law: You may not write production code until you have written a failing unit test.
Second Law: You may not write more of a unit test that is sufficient to fail, and not compiling is failing.
Third Law: You may not write more production code than is sufficient to pass the currently failing test.

## KEEPING TESTS CLEAN

Test code is just as important as production code. It is not a second-class citizen.
It requires thought, design, and care.
It must be kept as clean as production code.

### Tests Enable the change

If your tests are dirty, then your ability to change your code is hampered, and you begin to lose the ability to improve the structure of that code.

## CLEAN TESTS

Readability, readability, readability.

> Build Operate Check pattern

https://dkbalachandar.wordpress.com/2016/05/09/build-operate-check-acceptance-pattern/

```
public class ContactDAOTest{
    @Test
    public void testCreateContactData() throws Exception {
        // Build the test data
        Contact contact = buildContactData("bala","dublin");

        // Operate
        String id = new ContactDAO().create(contact);

        // Check
        assertNotNull(id);
    }
}
```

The tests get right to the point and use only the data types and functions that they truly need.

## FEW ASSERTS PER TEST

Quick and easy to understand.
It is ok to have more than one assert at the end.
Number of asserts in a test ought to be minimized.

### Single Concept per Test

Test a single concept in each test function.
