# Scala Option

- Scala programmers don't like null
- Options are modeled as `Some` or `None`
- `Some` contains answer to be extracted
- Extracting the answer can be done by calling `get`, `getOrElse`, pattern matching, or functions
- Scala still uses null to inter-operate with Java

*null reference is bad*

I call it my-billion-dollar mistake. It was the invention of the null reference in 1965. 
At that time, I was designing the first comprehensive type system for references in an object oriented language (ALGOL W).
My goal was to ensure that all use of references should be absolutely safe, with checking performed automatically by the compiler.
But I couldn't resist the temptation to put in a null reference, simply because it was so easy to implement.
This has led to lots of errors, vulnerabilities, and system crashes, which have probably caused a billion dollars of pain and damage in the last forty years.
