# RESTful Web Services with Dropwizard

## kill running localhost:8080

```
LMDV-WHAN:~ whan$ lsof -i:8080
lsof: WARNING: can't stat() smbfs file system /Volumes/whan
      Output information may be incomplete.
      assuming "dev=2e000004" from mount table
COMMAND     PID USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
Google     2816 whan  237u  IPv6 0x4069dba0cefdee85      0t0  TCP localhost:55679->localhost:http-alt (CLOSE_WAIT)
java      24057 whan  122u  IPv6 0x4069dba0d2fe1405      0t0  TCP *:http-alt (LISTEN)
LMDV-WHAN:~ whan$ kill 24057
```
