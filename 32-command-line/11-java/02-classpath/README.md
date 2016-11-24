# Managing the classpath

https://www.safaribooksonline.com/library/view/learning-path-oracle/9780134664088/OCA8_M02_L03_03.html

## compile 

>  `$ javac -d . MyProgram.java`

## run

### run inside the .java file folder

> `$ java mypackage.MyProgram`

### run anywhere

**java -cp**

> `$ java -cp /Users/user/java/myprogram mypackage.MyProgram` - `-cp` == `-classpath`

**CLASSPATH environment variable**

> `$ export CLASSPATH=/Users/user/java/myprogram`

> `$ java mypackage.MyProgram`

> `$ unset CLASSPATH`

## classpath

**specify .jar, .zip, code folder**

> `$ java -cp /javalib/one.jar:/javalib/two.zip:/otherlibs/*:/home/user/mycode mypackage.MyProgram`

**mac separate with :**

> `$ java -cp /javalib:/home/user/java/myprogram mypackage.MyProgram`

**windows separate with ;**

> `$ java -cp c:\javalib;c:\home\user\java\myprogram mypackage.MyProgram`
