# Selectively building modules

https://www.safaribooksonline.com/library/view/apache-maven-cookbook/9781785286124/ch09s07.html

> `parent` pom

*default*

```
  <modules>
    <module>default-child</module>
  </modules>
```

*profile*

```
  <profiles>
    <profile>
      <id>dev</id>
      <modules>
        <module>default-child</module>
        <module>profile-child</module>
      </modules>
    </profile>
  </profiles>
```

## Build

build at `parent` folder

> `default` build

`mvn clean test`

*build output - only default-child is built*

```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] parent ............................................. SUCCESS [  0.143 s]
[INFO] default-child ...................................... SUCCESS [  1.350 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

> `profile` build

`mvn -P dev clean test`

*build output - both default-child and profile-child are built*

```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] parent ............................................. SUCCESS [  0.112 s]
[INFO] default-child ...................................... SUCCESS [  1.051 s]
[INFO] profile-child ...................................... SUCCESS [  0.220 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
