# Multiline Strings and the "Slashy" String Syntax for Regular Expressions

```
groovy> String first = 'Graeme' 
groovy> String last = 'Rocher' 
groovy> assert "$first $last" == 'Graeme Rocher' 
```

## Multiple Strings - good for SQL

Single quote - no evaluation
```
groovy> def multilineString = ''' 
groovy> first line 
groovy> second line 
groovy> third line 
groovy> ''' 
 
Result: 
first line
second line
third line

groovy> println multilineString.readLines() 
[, first line, second line, third line]

groovy> println multilineString.readLines().size() 
4
```

Back slash - escape character
```
groovy> def multilineString = '''\ 
groovy> first line 
groovy> second line 
groovy> third line 
groovy> ''' 
groovy> println multilineString.readLines().size() 
3
```

Double quote - evaluate expression
```
groovy> def multilineString = """ 
groovy> first line ${0 + 1} 
groovy> second line ${0 + 2} 
groovy> third line ${0 + 3} 
groovy> """ 
groovy> println multilineString 
first line 1
second line 2
third line 3
```

## Slashy - for Regular Expressions

```
groovy> def zip = /\d{5}(-\d{4})?/ 
groovy> zip.getClass().getName() 
Result: java.lang.String

groovy> def zip = /\d{5}(-\d{4})?/ 
groovy> zip.getClass().getName() 
groovy> assert '12345' ==~ zip  // ==~ operator checks for an exact match
groovy> assert '12345-1234' ==~ zip 
groovy> assert '12345 12345-1234 1234'.findAll(zip) == ['12345', '12345-1234'] 

groovy> def testString = 'Flee to me, remote elf!'.toLowerCase().replaceAll(/\W/,'')  // uppercase W is non-word character
groovy> assert testString == 'fleetomeremoteelf' 
groovy> assert testString == testString.reverse() // test string is a palindrome 
```
