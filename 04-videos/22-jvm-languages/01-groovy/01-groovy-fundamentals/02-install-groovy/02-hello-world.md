# Running a "Hello, World" Script

## Groovy Shell

```
$ groovysh
groovy:000> :help
groovy:000> 1 + 1
===> 2
groovy:000> System.out.println("Hello, World!");
Hello, World
===> null
groovy:000> println("Hello, World!")
Hello, World
===> null
groovy:000> void sayHello(String name) {
groovy:001> println "Hello, ${name}!"
groovy:002> }
===> true
groovy:000> name = 'Tom'
===> Tom
groovy:000> sayHello(name)
Hello, Tom!
===> null
groovy:000> :x
```
