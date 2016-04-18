# Chapter 2. Time for injection

## Bootstrapping the injector

Once the injector has been bootstrapped, we can obtain an object from it for use.

- In the init lifecycle stage of a web app. Injector is bootstrapped in the init lifecycle stage of a web app, on deployment

```
                (deploy)               (deploy)
Web Server |------------------------------------------->
                   *                      *
                   ^                      ^
                   |                      |
          Bootstrap Injector      Bootstrap Injector
```

- On startup of a desktop program. Injector is bootstrapped during a desktop application's startup

```
* Desktop App |------------------------>
              ^
              |
     Bootstrap Injector
```

- On demand, injector is bootstrapped every time it is needed

```
                    (injector used)       (injector used)
* Desktop App |---------------------------------------------->
                           ^                      ^
                           |                      |
                   Bootstrap Injector     Boostrap Injector
```

- Lazily, injector is bootstrapped on first use

```
                    (injector used)
* Desktop App |------------------------>
                           ^                      ^
                           |                      |
                   Bootstrap Injector
```

## In-code configuration
Guice provide the notion of explicit configuration. The advantage of this approach is that you have a central directory
of all the services and dependencies used in a particular application. In a large project with many developers working
on the same source code, such a directory is especially useful. In Guice this file is called a Module.


