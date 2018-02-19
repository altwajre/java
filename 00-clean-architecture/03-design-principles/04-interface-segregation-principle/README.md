# Interface Segregation Principle (ISP)

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch10.xhtml

Statically typed languages like Java force programmers to create declarations that users must `import` or `include`.
It is these included declarations in source code that create the source code dependencies that force recompilation and redeployment.

Dynamically typed languages like Ruby and Python don't have declarations. They are inferred at runtime.
Thus there are no source code dependencies to force recompilation and redeployment.

## ISP and Architecture

It is harmful to depend on modules that contain more than you need.
