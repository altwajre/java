package com.company.app;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws Exception {

        ConvertToPojoTest.loadInvoice();

        logFile();

    }

    /*
    yaml:
    ---
    Time: 2001-11-23 15:01:42 -5
    User: ed
    Warning:
      This is an error message
      for the log file
    ---
    Time: 2001-11-23 15:02:31 -5
    User: ed
    Warning:
      A slightly different error
      message.
    ---
    Date: 2001-11-23 15:03:17 -5
    User: ed
    Fatal:
      Unknown variable "bar"
    Stack:
      - file: TopClass.py
        line: 23
        code: |
          x = MoreObject("345\n")
      - file: MoreClass.py
        line: 58
        code: |-
          foo = bar
     */
    private static void logFile() throws FileNotFoundException {
        String pathname = "src/main/resources/spec/02-log-file.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        for (Object data : yaml.loadAll(inputStream)){
            System.out.println(data);
        }
    }
    /*
    output:
    {Time=Fri Nov 23 12:01:42 PST 2001, User=ed, Warning=This is an error message for the log file}
    {Time=Fri Nov 23 12:02:31 PST 2001, User=ed, Warning=A slightly different error message.}
    {Date=Fri Nov 23 12:03:17 PST 2001, User=ed, Fatal=Unknown variable "bar", Stack=[{file=TopClass.py, line=23, code=x = MoreObject("345\n")
    }, {file=MoreClass.py, line=58, code=foo = bar}]}
     */
}
