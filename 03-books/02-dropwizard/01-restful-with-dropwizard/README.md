# RESTful Web Services with Dropwizard

https://www.safaribooksonline.com/library/view/restful-web-services/9781783289530/

## kill running localhost:8080

```
$ lsof -i:8080
lsof: WARNING: can't stat() smbfs file system /Volumes/user
      Output information may be incomplete.
      assuming "dev=2e000004" from mount table
COMMAND     PID USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
Google     2816 user  237u  IPv6 0x4069dba0cefdee85      0t0  TCP localhost:55679->localhost:http-alt (CLOSE_WAIT)
java      24057 user  122u  IPv6 0x4069dba0d2fe1405      0t0  TCP *:http-alt (LISTEN)
$ kill 24057
```
