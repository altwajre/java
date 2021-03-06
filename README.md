# java
java

## Uninstall Java

sudo rm -rf /Library/Java/JavaVirtualMachines/jdk(version).jdk
sudo rm -rf /Library/PreferencePanes/JavaControlPanel.prefPane
sudo rm -rf /Library/Internet\ Plug-Ins/JavaAppletPlugin.plugin
sudo rm -rf ~/Library/Application\ Support/Oracle/Java

## Install Java

https://gist.github.com/JeOam/a926dbb5145c4d0789c1

> brew install java 8

```
brew cask install caskroom/versions/java8
java -version
ls /Library/Java/JavaVirtualMachines/
jdk1.8.0_162.jdk
```

> brew install Maven

- set JAVA_HOME in ~/.bash_profile

```
atom ~/.bash_profile
```

add following to the end of the ~/.bash_profile

```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home
echo $JAVA_HOME
```

- install maven

```
brew install maven
mvn -version
```

- brew install java 9
brew cask install java
brew cask uninstall java

- install maven on windows

https://www.mkyong.com/maven/how-to-install-maven-in-windows/

## Install IDE

### Intellij

- install Intellij Community Edition
https://www.jetbrains.com/idea/

- setup SDK - Java Home
path is `/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home`

- clear caches

/Users/whan/Library/Caches

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

- Install Java 8 JDK
download JDK `Windows x64`
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
http://www.oracle.com/technetwork/java/javase/downloads/index.html

- JDK path
`C:\Program Files\Java\jdk1.8.0_102`

- JAVA_HOME
https://www.mkyong.com/java/how-to-set-java_home-on-windows-10/
