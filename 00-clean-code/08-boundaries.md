# Boundaries

https://www.safaribooksonline.com/library/view/clean-code/9780136083238/chapter08.html#ch8

## USING THIRD-PARTY CODE

There is a difference between the provider of an interface and the user of an interface.
Providers of third-party packages and frameworks strive for broad applicability so that can work in many environments and appeal to a wide audience.
Users, on the other hand, want an interface that is focused on their particular needs.

Not to pass Maps (or any other interface at a boundary) around your system.
If you use a boundary interface like Map, keep it inside the class, or close family of classes, where it is used.
Avoid returning it from, or accepting it as an argument to, public APIs.
