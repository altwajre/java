# stub

A stub delivers indirect inputs to the caller when the stub's methods are called. Stubs are programmed only for the test
scope. Stubs may record other information such as how many times they are invoked and so on.

Stubs are very handy to impersonate error conditions and external dependencies (you can achieve the same thing with a
mock; this is just one approach).