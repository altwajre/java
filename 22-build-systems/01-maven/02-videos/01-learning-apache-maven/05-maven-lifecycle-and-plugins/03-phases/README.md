# Phases

https://www.safaribooksonline.com/library/view/learning-apache-maven/9781771373661/video212511.html

## Important Phases

> Compile

Compile the source code of the project

> Test-compile

Compile the test source code into the test destination directory

> Test

Run tests using a suitable unit testing framework (junit or testng). 
These tests should not require the code be packaged or deployed

> Package

Take the compiled code and package it in its distributable format, such as a JAR

> Install

Install the package into the local repository, for use as a dependency in other projects locally

- Deploy

Done in an integration or release environment, copies the final package to the remote repository for sharing with other
developers and projects
