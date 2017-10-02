# Get gradle home

**gradle home**
/Users/whan/.sdkman/candidates/gradle/2.14.1

## Steps:

1, create a gradle project

2, add following to build.gradle
```
task getHomeDir << {
    println gradle.gradleHomeDir
}
```

3, run `gradle getHomeDir`
