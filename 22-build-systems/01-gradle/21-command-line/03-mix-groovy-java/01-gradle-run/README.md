# Mix Groovy and Java gradle run command line

https://discuss.gradle.org/t/how-to-compile-groovy-sources-mixed-with-java-sources/6859/3

## modify build.gradle

- del `src/main/java`

- add all the groovy and java code `src/main/groovy`

- remove `apply plugin: 'java'`

- add `apply plugin: 'application'`

- add `mainClassName = 'com.company.app.App'`

- add following

```
run {
    if(project.hasProperty("appArgs")){
        args Eval.me(appArgs)
    }
}
```

## run

**no arg**

- `$ gradle clean run`

```
# Hello
Product name: phone
```

**pass args**

- `$ gradle clean run -PappArgs="['a','b']"`

```
# Hello
a
b
Product name: phone
```
