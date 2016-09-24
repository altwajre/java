# Plain Old Groovy Objects (POGOs)

POGO
```
groovy> class Person { 
groovy>   String first 
groovy>   String last 
groovy>    
groovy>   void setLast(String last) { 
groovy>     println 'inside setLast' 
groovy>     this.last = last 
groovy>   } 
groovy> } 
groovy> Person p = new Person() 
groovy> p.setFirst('Tom') 
groovy> p.last = 'Lee' 
groovy> println "${p.getFirst()} ${p.last}" 
 
inside setLast
Tom Lee
```

Constructor - map-base constructor
```
groovy> class Person { 
groovy>   String first 
groovy>   String last 
groovy>    
groovy>   void setLast(String last) { 
groovy>     println 'inside setLast' 
groovy>     this.last = last 
groovy>   } 
groovy> } 
groovy> Person p = new Person(first: 'Tom', last: 'Lee') 
groovy> println "${p.getFirst()} ${p.last}" 
 
inside setLast
Tom Lee
```

toString()
```
groovy> class Person { 
groovy>   String first 
groovy>   String last 
groovy>    
groovy>   void setLast(String last) { 
groovy>     println 'inside setLast' 
groovy>     this.last = last 
groovy>   } 
groovy>    
groovy>   String toString() { "$first $last" } 
groovy> } 
groovy> Person p = new Person(first: 'Tom', last: 'Lee') 
groovy> println p.toString() 
 
inside setLast
Tom Lee
```

## AST transformation - abstract syntax tree transformation

@ToString
```
groovy> import groovy.transform.* 
groovy> @ToString 
groovy> class Person { 
groovy>   String first 
groovy>   String last   
groovy> } 
groovy> Person p = new Person(first: 'Tom', last: 'Lee') 
groovy> println p.toString() 
 
Person(Tom, Lee)
```

@ToString(includeNames = true)
```
groovy> import groovy.transform.* 
groovy> @ToString(includeNames = true) 
groovy> class Person { 
groovy>   String first 
groovy>   String last   
groovy> } 
groovy> Person p = new Person(first: 'Tom', last: 'Lee') 
groovy> println p.toString() 
 
Person(first:Tom, last:Lee)
```

@EqualsAndHashCode
```
groovy> import groovy.transform.* 
groovy> @ToString @EqualsAndHashCode 
groovy> class Person { 
groovy>   String first 
groovy>   String last   
groovy> } 
groovy> Person p1 = new Person(first: 'Tom', last: 'Lee') 
groovy> Person p2 = new Person(first: 'Tom', last: 'Lee') 
groovy> Person p3 = new Person(first: 'Harry', last: 'Wong') 
groovy> println p1 == p2 
groovy> println p1 != p3 
 
true
true
```

Set - remove duplicate
```
groovy> import groovy.transform.* 
groovy> @ToString @EqualsAndHashCode 
groovy> class Person { 
groovy>   String first 
groovy>   String last   
groovy> } 
groovy> Person p1 = new Person(first: 'Tom', last: 'Lee') 
groovy> Person p2 = new Person(first: 'Tom', last: 'Lee') 
groovy> Person p3 = new Person(first: 'Harry', last: 'Wong') 
groovy> Set people = [p1, p2, p3] 
groovy> println people.size() 
 
2
```

@TupleConstructor - takes args inorder from top to bottom
```
import groovy.transform.*

@ToString @EqualsAndHashCode
@TupleConstructor
class Person {
  String first
  String last  
}

Person p1 = new Person(first: 'Tom', last: 'Lee')
Person p2 = new Person(first: 'Tom', last: 'Lee')
Person p3 = new Person(first: 'Harry', last: 'Wong')
Person p4 = new Person('Tom', 'Lee') // TupleConstructor is used here
Set people = [p1, p2, p3, p4]
println people.size()
```

@Canonical - including @ToString @EqualsAndHashCode @TupleConstructor
```
groovy> import groovy.transform.* 
groovy> @Canonical 
groovy> class Person { 
groovy>   String first 
groovy>   String last   
groovy> } 
groovy> Person p1 = new Person(first: 'Tom', last: 'Lee') 
groovy> Person p2 = new Person(first: 'Tom', last: 'Lee') 
groovy> Person p3 = new Person(first: 'Harry', last: 'Wong') 
groovy> Person p4 = new Person('Tom', 'Lee') // TupleConstructor is used here 
groovy> Set people = [p1, p2, p3, p4] 
groovy> println people.size() 
 
2
```
