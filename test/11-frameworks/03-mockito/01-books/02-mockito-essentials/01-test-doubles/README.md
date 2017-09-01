# test doubles

> `Doubles` includes following

- `Dummy` - a dummy object is passed as a mandatory parameter object. A dummy object is not directly used in the test
or code under test, but it is required for the creation of another object required in the code under test.
```
class DummyStudent extends Student{ // dummy object
    DummyStudent() {
        super(null, null);
    }
    @Override
    public String getRoleNumber(){
        throw new RuntimeException("Dummy student");
    }
    @Override
    public String getName(){
        throw new RuntimeException("Dummy student");
    }
}
```

- `Stub` - a stub delivers indirect inputs to the caller when the stub's methods are called. Stubs are very handy to
impersonate error conditions and external dependencies (you can achieve the same thing with a mock; this is just one approach).
```
class ConnectionTimeoutStudentDaoStub implements StudentDao { // stub object
    public String create(String name, String className) throws SQLException {
        throw new SQLException("DB connection timed out");
    }
}
```

- `Spy` - a spy secretly obtains the information of a rival or someone very important. A spy object spies on a real
object. A spy is a variation of a stub, but instead of only setting the expectation, a spy records the method calls made
to the collaborator. A spy can act as an indirect output of the unit under test and can also act as an audit log.

- `Mock` - a mock object is a combination of a spy and a stub. It acts as an indirect output for a code under test, such
as a spy, and also stub methods to return values or throw exceptions, like a stub. A mock object fails a test if an
expected method is not invoked or if the parameters of the method don’t match.

- `Fake` - a fake object is a test double with real logic (unlike stubs) and is much more simplified or cheaper in some
way. We do not mock or stub a unit that we test; rather, the external dependencies of the unit are mocked or stubbed so
that the output of the dependent objects can be controlled or observed from the tests. The fake object replaces the
functionality of the real code that we want to test.
```
    class TestableStudentDao extends StudentDaoImpl { // fake object
        int[] valuesToReturn;

        @Override
        int[] update(String sql, List<Map<String, Object>> params) {
            Integer count = sqlCount.get(sql);
            if (count == null) {
                sqlCount.put(sql, params.size());
            } else {
                sqlCount.put(sql, count + params.size());
            }

            if (valuesToReturn != null) {
                return valuesToReturn;
            }

            int[] val = new int[params.size()];
            for (int i = 0; i < params.size(); i++) {
                val[i] = 1;
            }
            return val;
        }
    }
```
