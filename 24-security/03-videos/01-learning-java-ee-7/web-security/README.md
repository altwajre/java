# Web Security

https://www.safaribooksonline.com/library/view/learning-java-ee/9781771371940/video170344.html

https://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html

> SSL Generating The Keystore File

https://www.safaribooksonline.com/library/view/learning-java-ee/9781771371940/video170349.html

> SSL Configuring The Tomcat Server XML File

https://www.safaribooksonline.com/library/view/learning-java-ee/9781771371940/video170350.html

> Tomcat location

``
/Library/Tomcat
``

## generate `tomcat` certificate in the keystore file

> Create a keystore file to store the server's private key and self-signed certificate

```
$ keytool -genkey -alias tomcat -keyalg RSA
Enter keystore password:  changeit
What is your first and last name?
  [Unknown]:  William Han
What is the name of your organizational unit?
  [Unknown]:  
What is the name of your organization?
  [Unknown]:  godaddy
What is the name of your City or Locality?
  [Unknown]:  
What is the name of your State or Province?
  [Unknown]:  
What is the two-letter country code for this unit?
  [Unknown]:  
Is CN=William Han, OU=Unknown, O=godaddy, L=Unknown, ST=Unknown, C=Unknown correct?
  [no]:  yes

Enter key password for <tomcat>
	(RETURN if same as keystore password): changeit  
Re-enter new password: changeit
```

> list keystore

```
$ keytool -list -v
Enter keystore password:  changeit

Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

Alias name: tomcat
Creation date: Jul 28, 2017
Entry type: PrivateKeyEntry
Certificate chain length: 1
Certificate[1]:
Owner: CN=William Han, OU=Unknown, O=godaddy, L=Unknown, ST=Unknown, C=Unknown
Issuer: CN=William Han, OU=Unknown, O=godaddy, L=Unknown, ST=Unknown, C=Unknown
Serial number: 32fc8c0f
Valid from: Fri Jul 28 15:00:09 PDT 2017 until: Thu Oct 26 15:00:09 PDT 2017
Certificate fingerprints:
	 MD5:  14:FC:C7:A6:B6:4E:B8:0E:BE:0A:2B:1D:B4:C4:36:57
	 SHA1: 3E:87:0B:27:60:1D:4C:C4:37:0D:8F:66:5A:24:A6:26:53:29:CD:A0
	 SHA256: FF:64:58:60:FF:71:5A:AB:65:B7:2F:8E:FF:1C:3B:67:17:08:22:E1:06:17:AB:1A:AE:67:6E:57:A3:D2:88:14
	 Signature algorithm name: SHA256withRSA
	 Version: 3

Extensions: 

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: A6 FF 6F 04 30 63 41 A8   95 DD 91 EF D4 05 74 3B  ..o.0cA.......t;
0010: 2C B1 2E 52                                        ,..R
]
]

*******************************************
*******************************************
```

> optional: delete `tomcat` certificate

```
$ keytool -delete -alias tomcat
Enter keystore password:  changeit

$ keytool -list -v
Enter keystore password:  changeit

Keystore type: JKS
Keystore provider: SUN

Your keystore contains 0 entries
```

## Edit Tomcat Config

> add following port `8443` connector to `/Library/Tomcat/conf/server.xml` 

```
<!-- Define a SSL Coyote HTTP/1.1 Connector on port 8443 -->
<Connector
           protocol="org.apache.coyote.http11.Http11NioProtocol"
           port="8443" maxThreads="200"
           scheme="https" secure="true" SSLEnabled="true"
           keystoreFile="${user.home}/.keystore" keystorePass="changeit"
           clientAuth="false" sslProtocol="TLS"/>
```

> Restart `Tomcat` - Stop and start `Tomcat`

```
$ bin/shutdown.sh 
Using CATALINA_BASE:   /Library/Tomcat
Using CATALINA_HOME:   /Library/Tomcat
Using CATALINA_TMPDIR: /Library/Tomcat/temp
Using JRE_HOME:        /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
Using CLASSPATH:       /Library/Tomcat/bin/bootstrap.jar:/Library/Tomcat/bin/tomcat-juli.jar
$ bin/startup.sh 
Using CATALINA_BASE:   /Library/Tomcat
Using CATALINA_HOME:   /Library/Tomcat
Using CATALINA_TMPDIR: /Library/Tomcat/temp
Using JRE_HOME:        /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
Using CLASSPATH:       /Library/Tomcat/bin/bootstrap.jar:/Library/Tomcat/bin/tomcat-juli.jar
Tomcat started.
```

> https://localhost:8443/

Result: `Not Secure`
