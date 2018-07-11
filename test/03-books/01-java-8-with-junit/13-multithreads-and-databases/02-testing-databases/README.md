# Testing Databases

https://www.safaribooksonline.com/library/view/pragmatic-unit-testing/9781680500769/f_0121.html

> Unit tests - mock

StatCompilerTest.questionTextDoesStuffBDD()

> Integration tests

These slower database tests will prove that everything is wired together correctly. Defects are fairly common in
dealing with JPA and JDBC.

If you isolate all of your persistence interaction to one place in the system, you end up with a reasonably small
amount of code that must be integration-tested.

Prefer to let the tests create and manage the data.

Each test is responsible for adding and working with its own data. This minimizes intertest dependency issues, where
one test breaks because of data that another test left lying around.

Integration tests are essential but challenging to design and maintain. Minimize their number and complexity by
maximizing the logic you verify in unit tests.
