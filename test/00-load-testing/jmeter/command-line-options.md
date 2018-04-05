# JMeter command line options

https://www.safaribooksonline.com/library/view/jmeter-30-advanced/9781787289673/video4_5.html

jmeter -n -t Script.jmx -l Report.csv
jmeter -n -t Script.jmx -l Report.jtl

> command line options

```
-n - specifies JMeter to run the test in non-GUI mode
-t - specifies the location of test plan and also name of the JMX file that contains the Test Plan
-l - specifies the log file name of JTL file in which results are logged at runtime
```

> ensure `summariser.name=summary` is uncommented

cat /usr/local/Cellar/jmeter/3.3/libexec/bin/jmeter.properties  | grep summariser.name=summary
summariser.name=summary
