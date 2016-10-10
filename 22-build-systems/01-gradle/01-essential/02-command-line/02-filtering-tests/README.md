# Filtering Tests

check out 46.13.3. Test filtering below
https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_test

**use groovy plugin**

```
apply plugin: 'groovy'
```

**add Spock and junit dependencies**

```
dependencies {
    testCompile 'org.spockframework:spock-core:1.0-groovy-2.4'
    testCompile 'junit:junit:4.12'
}
```

**add spock test**

```
src/test/groovy
```

**run test in command line**

```
$ gradle clean test
```

*run smoke only*

```
$ gradle test --tests com.company.spock.smoke.*
```

*run stress only*

```
$ gradle test --tests com.company.spock.stress.*
```

**test report**

```
$ open build/reports/tests/index.html 
```
