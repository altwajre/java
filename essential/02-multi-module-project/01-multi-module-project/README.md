# multi modules project

## Intellij

### Parent
1, Create a maven quickstart project - groupId=com.company.test
2, In pom.xml, change packaging to pom
3, Delete src folder

### Lib
1, Right click on the parent project, New, Module, select maven quickstart - groupId=com.company.test.lib, parent=com.company.test

### App
1, Right click on the parent project, New, Module, select maven quickstart - groupId=com.company.test.app, parent=com.company.test
2, Add lib module as dependency
```
    <dependencies>
        <dependency>
            <groupId>com.company.test.lib</groupId>
            <artifactId>lib</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
```

