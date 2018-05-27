# GRADLE HOME

- build.gradle

```
task getHomeDir << {
    println gradle.gradleHomeDir
}
```

- Run following to get GRADLE_HOME

gradle getHomeDir

## intellij

> Add gradle home

- goto `Preferences`, `Build, Execution, Deployment`, `Build Tools`, `Gradle`, `Service directory path:`
- enter `/usr/local/Cellar/gradle/4.7/libexec`

> Create a new gradle project

- the first new gradle project will download gradle dependencies

> Open existing gradle project

- check Use auto-import
- check Create directories for empty content roots automatically
- select Use gradle `wrapper` task configuration
