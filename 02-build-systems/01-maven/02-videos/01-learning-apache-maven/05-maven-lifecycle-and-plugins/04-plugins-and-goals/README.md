# Plugins and Goals

https://www.safaribooksonline.com/library/view/learning-apache-maven/9781771373661/video212512.html

Plugin = class

Goal = method

`mvn plugin:goal`

> compiler plugin

```
$ mvn compiler:compile
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building plugins-and-goals 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-cli) @ plugins-and-goals ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target/classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.118 s
[INFO] Finished at: 2017-08-06T22:48:06-07:00
[INFO] Final Memory: 13M/206M
[INFO] ------------------------------------------------------------------------
```

> super pom - `mvn help:effective-pom`

get compiler plugin out of the super pom below

```
  <plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.1</version>
    <executions>
      <execution>
        <id>default-compile</id>
        <phase>compile</phase>
        <goals>
          <goal>compile</goal>
        </goals>
      </execution>
      <execution>
        <id>default-testCompile</id>
        <phase>test-compile</phase>
        <goals>
          <goal>testCompile</goal>
        </goals>
      </execution>
    </executions>
  </plugin>
```

```
LMDV-WHAN:plugins-and-goals whan$ mvn help:effective-pom
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building plugins-and-goals 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-help-plugin:2.2:effective-pom (default-cli) @ plugins-and-goals ---
[INFO] 
Effective POMs, after inheritance, interpolation, and profiles are applied:

<!-- ====================================================================== -->
<!--                                                                        -->
<!-- Generated by Maven Help Plugin on 2017-08-06T10:50:21                  -->
<!-- See: http://maven.apache.org/plugins/maven-help-plugin/                -->
<!--                                                                        -->
<!-- ====================================================================== -->

<!-- ====================================================================== -->
<!--                                                                        -->
<!-- Effective POM for project                                              -->
<!-- 'com.company.app:plugins-and-goals:jar:1.0-SNAPSHOT'                   -->
<!--                                                                        -->
<!-- ====================================================================== -->

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.company.app</groupId>
  <artifactId>plugins-and-goals</artifactId>
  <version>1.0-SNAPSHOT</version>
  <name>plugins-and-goals</name>
  <url>http://maven.apache.org</url>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <repositories>
    <repository>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
    </repository>
  </repositories>
  <pluginRepositories>
    <pluginRepository>
      <releases>
        <updatePolicy>never</updatePolicy>
      </releases>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
    </pluginRepository>
  </pluginRepositories>
  <build>
    <sourceDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/src/main/java</sourceDirectory>
    <scriptSourceDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/src/main/scripts</scriptSourceDirectory>
    <testSourceDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/src/test/java</testSourceDirectory>
    <outputDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target/classes</outputDirectory>
    <testOutputDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target/test-classes</testOutputDirectory>
    <resources>
      <resource>
        <directory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/src/main/resources</directory>
      </resource>
    </resources>
    <testResources>
      <testResource>
        <directory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/src/test/resources</directory>
      </testResource>
    </testResources>
    <directory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target</directory>
    <finalName>plugins-and-goals-1.0-SNAPSHOT</finalName>
    <pluginManagement>
      <plugins>
        <plugin>
          <artifactId>maven-antrun-plugin</artifactId>
          <version>1.3</version>
        </plugin>
        <plugin>
          <artifactId>maven-assembly-plugin</artifactId>
          <version>2.2-beta-5</version>
        </plugin>
        <plugin>
          <artifactId>maven-dependency-plugin</artifactId>
          <version>2.8</version>
        </plugin>
        <plugin>
          <artifactId>maven-release-plugin</artifactId>
          <version>2.3.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
    <plugins>
      <plugin>
        <artifactId>maven-clean-plugin</artifactId>
        <version>2.5</version>
        <executions>
          <execution>
            <id>default-clean</id>
            <phase>clean</phase>
            <goals>
              <goal>clean</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-resources-plugin</artifactId>
        <version>2.6</version>
        <executions>
          <execution>
            <id>default-testResources</id>
            <phase>process-test-resources</phase>
            <goals>
              <goal>testResources</goal>
            </goals>
          </execution>
          <execution>
            <id>default-resources</id>
            <phase>process-resources</phase>
            <goals>
              <goal>resources</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.4</version>
        <executions>
          <execution>
            <id>default-jar</id>
            <phase>package</phase>
            <goals>
              <goal>jar</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <executions>
          <execution>
            <id>default-compile</id>
            <phase>compile</phase>
            <goals>
              <goal>compile</goal>
            </goals>
          </execution>
          <execution>
            <id>default-testCompile</id>
            <phase>test-compile</phase>
            <goals>
              <goal>testCompile</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.12.4</version>
        <executions>
          <execution>
            <id>default-test</id>
            <phase>test</phase>
            <goals>
              <goal>test</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-install-plugin</artifactId>
        <version>2.4</version>
        <executions>
          <execution>
            <id>default-install</id>
            <phase>install</phase>
            <goals>
              <goal>install</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-deploy-plugin</artifactId>
        <version>2.7</version>
        <executions>
          <execution>
            <id>default-deploy</id>
            <phase>deploy</phase>
            <goals>
              <goal>deploy</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-site-plugin</artifactId>
        <version>3.3</version>
        <executions>
          <execution>
            <id>default-site</id>
            <phase>site</phase>
            <goals>
              <goal>site</goal>
            </goals>
            <configuration>
              <outputDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target/site</outputDirectory>
              <reportPlugins>
                <reportPlugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-project-info-reports-plugin</artifactId>
                </reportPlugin>
              </reportPlugins>
            </configuration>
          </execution>
          <execution>
            <id>default-deploy</id>
            <phase>site-deploy</phase>
            <goals>
              <goal>deploy</goal>
            </goals>
            <configuration>
              <outputDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target/site</outputDirectory>
              <reportPlugins>
                <reportPlugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-project-info-reports-plugin</artifactId>
                </reportPlugin>
              </reportPlugins>
            </configuration>
          </execution>
        </executions>
        <configuration>
          <outputDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target/site</outputDirectory>
          <reportPlugins>
            <reportPlugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-project-info-reports-plugin</artifactId>
            </reportPlugin>
          </reportPlugins>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <reporting>
    <outputDirectory>/Users/whan/Desktop/java/22-build-systems/01-maven/02-videos/01-learning-apache-maven/05-maven-lifecycle-and-plugins/04-plugins-and-goals/plugins-and-goals/target/site</outputDirectory>
  </reporting>
</project>

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.595 s
[INFO] Finished at: 2017-08-06T22:50:21-07:00
[INFO] Final Memory: 10M/245M
[INFO] ------------------------------------------------------------------------
```

> describe compiler plugin - `mvn help:describe -Dplugin=compiler`

- compiler:compile = plugin:goal
- compile goal attaches to compile phase
- goal attaches different phase

```
LMDV-WHAN:plugins-and-goals whan$ mvn help:describe -Dplugin=compiler
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building plugins-and-goals 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-help-plugin:2.2:describe (default-cli) @ plugins-and-goals ---
Downloading: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/maven-metadata.xml
Downloaded: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/maven-metadata.xml (955 B at 2.3 kB/s)
Downloading: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.6.2/maven-compiler-plugin-3.6.2.pom
Downloaded: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.6.2/maven-compiler-plugin-3.6.2.pom (11 kB at 231 kB/s)
Downloading: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.6.2/maven-compiler-plugin-3.6.2.jar
Downloaded: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.6.2/maven-compiler-plugin-3.6.2.jar (75 kB at 952 kB/s)
[INFO] org.apache.maven.plugins:maven-compiler-plugin:3.6.2

Name: Apache Maven Compiler Plugin
Description: The Compiler Plugin is used to compile the sources of your
  project.
Group Id: org.apache.maven.plugins
Artifact Id: maven-compiler-plugin
Version: 3.6.2
Goal Prefix: compiler

This plugin has 3 goals:

compiler:compile
  Description: Compiles application sources

compiler:help
  Description: Display help information on maven-compiler-plugin.
    Call mvn compiler:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

compiler:testCompile
  Description: Compiles application test sources.

For more information, run 'mvn help:describe [...] -Ddetail'

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.468 s
[INFO] Finished at: 2017-08-06T22:54:05-07:00
[INFO] Final Memory: 13M/212M
[INFO] ------------------------------------------------------------------------
```

> view compiler plugin help goal - `$ mvn compiler:help -Ddetail=true -Dgoal=compile`

```
LMDV-WHAN:plugins-and-goals whan$ mvn compiler:help -Ddetail=true -Dgoal=compile
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building plugins-and-goals 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:help (default-cli) @ plugins-and-goals ---
[INFO] Maven Compiler Plugin 3.1
  The Compiler Plugin is used to compile the sources of your project.

compiler:compile
  Compiles application sources

  Available parameters:

    annotationProcessors
      Names of annotation processors to run. Only applies to JDK 1.6+ If not
      set, the default annotation processors discovery process applies.

    compilerArgs
      Sets the arguments to be passed to the compiler if fork is set to true.
      Example:
      
      <compilerArgs>
       <arg>-Xmaxerrs=1000</arg>
       <arg>-Xlint</arg>
      </compilerArgs>

    compilerArgument
      Sets the unformatted single argument string to be passed to the compiler
      if fork is set to true. To pass multiple arguments such as -Xmaxerrs 1000
      (which are actually two arguments) you have to use compilerArguments.
      
      This is because the list of valid arguments passed to a Java compiler
      varies based on the compiler version.

    compilerArguments
      Sets the arguments to be passed to the compiler (prepending a dash) if
      fork is set to true.
      
      This is because the list of valid arguments passed to a Java compiler
      varies based on the compiler version.
      
      To pass -Xmaxerrs 1000 -Xlint -Xlint:-path -Averbose=true you should
      include the following:
      
      <compilerArguments>
       <Xmaxerrs>1000</Xmaxerrs>
       <Xlint/>
       <Xlint:-path/>
       <Averbose>true</Averbose>
      </compilerArguments>

    compilerId
      The compiler id of the compiler to use. See this guide for more
      information.

    compilerReuseStrategy
      Strategy to re use javacc class created:
      - reuseCreated (default): will reuse already created but in case of
        multi-threaded builds, each thread will have its own instance
      - reuseSame: the same Javacc class will be used for each compilation even
        for multi-threaded build
      - alwaysNew: a new Javacc class will be created for each compilation
      Note this parameter value depends on the os/jdk you are using, but the
      default value should work on most of env.

    compilerVersion
      Version of the compiler to use, ex. '1.3', '1.5', if fork is set to true.

    debug
      Set to true to include debugging information in the compiled class files.

    debuglevel
      Keyword list to be appended to the -g command-line switch. Legal values
      are none or a comma-separated list of the following keywords: lines, vars,
      and source. If debug level is not specified, by default, nothing will be
      appended to -g. If debug is not turned on, this attribute will be ignored.

    encoding
      The -encoding argument for the Java compiler.

    excludes
      A list of exclusion filters for the compiler.

    executable
      Sets the executable of the compiler to use when fork is true.

    failOnError
      Indicates whether the build will continue even if there are compilation
      errors.

    fileExtensions
      file extensions to check timestamp for incremental build default contains
      only .class

    forceJavacCompilerUse
      compiler can now use javax.tools if available in your current jdk, you can
      disable this feature using -Dmaven.compiler.forceJavacCompilerUse=true or
      in the plugin configuration

    fork
      Allows running the compiler in a separate process. If false it uses the
      built in compiler, while if true it will use an executable.

    generatedSourcesDirectory
      Specify where to place generated source files created by annotation
      processing. Only applies to JDK 1.6+

    includes
      A list of inclusion filters for the compiler.

    maxmem
      Sets the maximum size, in megabytes, of the memory allocation pool, ex.
      '128', '128m' if fork is set to true.

    meminitial
      Initial size, in megabytes, of the memory allocation pool, ex. '64', '64m'
      if fork is set to true.

    mojoExecution
      

    optimize
      Set to true to optimize the compiled code using the compiler's
      optimization methods.

    outputFileName
      Sets the name of the output file when compiling a set of sources to a
      single file. expression='${project.build.finalName}'

    proc
      Sets whether annotation processing is performed or not. Only applies to
      JDK 1.6+ If not set, both compilation and annotation processing are
      performed at the same time.
      
      Allowed values are:
      
      - none - no annotation processing is performed.
      - only - only annotation processing is done, no compilation.

    showDeprecation
      Sets whether to show source locations where deprecated APIs are used.

    showWarnings
      Set to true to show compilation warnings.

    skipMain
      Set this to 'true' to bypass compilation of main sources. Its use is NOT
      RECOMMENDED, but quite convenient on occasion.

    skipMultiThreadWarning
      

    source
      The -source argument for the Java compiler.

    staleMillis
      Sets the granularity in milliseconds of the last modification date for
      testing whether a source needs recompilation.

    target
      The -target argument for the Java compiler.

    useIncrementalCompilation
      to enable/disable incrementation compilation feature

    verbose
      Set to true to show messages about what the compiler is doing.


[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.522 s
[INFO] Finished at: 2017-08-06T23:00:15-07:00
[INFO] Final Memory: 9M/309M
[INFO] ------------------------------------------------------------------------
```
