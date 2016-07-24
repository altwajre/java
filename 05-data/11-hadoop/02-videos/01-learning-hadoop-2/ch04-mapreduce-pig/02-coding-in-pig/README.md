# Coding 'Word Count' in Pig

*cludera*

- `Query Editors`, `Pig`

- enter following script

```
A = load 'Names.txt';
B = foreach A generate flatten (TOKENIZE((chararray)$0)) as word;
C = group B by word;
D = foreach C generate COUNT(B), group;
store D into 'wordcount';
```

- save it, click the `Play` button to execute the script

- waiting for the job to be completed
 
- `File Browser`, `/user/cloudera/wordcount/part-r-00000`

```
4	Aaron
1	Laura
3	Sarah
3	Meredith
```
