# fake

A fake object is a test double with real logic (unlike stubs) and is much more simplified or cheaper in some way. We do
not mock or stub a unit that we test; rather, the external dependencies of the unit are mocked or stubbed so that the
output of the dependent objects can be controlled or observed from the tests. The fake object replaces the functionality
of the real code that we want to test. Fakes are also dependencies, and don't mock via subclassing (which is generally
always a bad idea; use composition instead.) Fakes aren't just stubbed return values; they use some real logic.
A classic example is to use a database stub that always returns a fixed value from the DB, or a DB fake, which is an
entirely in-memory non-persistent database that's otherwise fully functional.
Fake objects are working implementations. Mostly, the fake class extends the original class, but it usually performs
hacking, which makes it unsuitable for production.



