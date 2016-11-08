# gradle run

## modify build.gradle

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
```

**pass args**

- `$ gradle run -PappArgs="['a','b']"`

```
# Hello
a
b
```
