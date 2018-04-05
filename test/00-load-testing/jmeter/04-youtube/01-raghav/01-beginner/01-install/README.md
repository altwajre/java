# Install Jmeter

https://www.youtube.com/watch?v=M-iAXz8vs48&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

## brew install

brew install jmeter
brew install jmeter --with-plugins

jmeter --version

/usr/local/Cellar/jmeter/3.3

## centos

download from http://jmeter.apache.org/download_jmeter.cgi

sudo wget http://www.gtlib.gatech.edu/pub/apache//jmeter/binaries/apache-jmeter-3.3.tgz

sudo tar -xf apache-jmeter-3.3.tgz
sudo mv apache-jmeter-3.3 /opt/
/opt/apache-jmeter-3.3/bin/jmeter

- add to PATH

```
$ sudo nano /usr/local/bin/jmeter
#!/bin/bash
exec "/opt/apache-jmeter-3.3/bin/jmeter" "$@"
$ sudo chmod +x jmeter
```
