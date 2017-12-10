# java
java

## Install Java

https://gist.github.com/JeOam/a926dbb5145c4d0789c1

- brew install java
brew update
brew tap caskroom/cask
brew install Caskroom/cask/java
java -version

- brew install Maven
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
brew install maven

## Install IDE

### Intellij

- install Intellij Community Edition
https://www.jetbrains.com/idea/

- setup SDK - Java Home
path is `/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home`

- Open Java Project
Open pom.xml

- References
https://www.jetbrains.com/idea/help/keyboard-shortcuts-you-cannot-miss.html

```
Shortcut	Description
Alt Enter	Show the list of available intention actions.
```

- Path
echo $JAVA_HOME

- Edit Path Environment
atom ~/.bash_profile
touch ~/.bash_profile; open ~/.bash_profile
export ORGANIZATION="Tony's Pizza" - set environment variable
echo $ORGANIZATION

- kb
Error: Could not find or load main class @{surefireArgLine}
Solution: `Build, Execution, Deployment`, `Build Tools`, `Maven`, `Running Tests`, uncheck `argline`

## Windows

note: download and install `Windows x64` packages

- Install Java JDK
download JDK `Windows x64`
http://www.oracle.com/technetwork/java/javase/downloads/index.html

- JDK path
`C:\Program Files\Java\jdk1.8.0_102`
