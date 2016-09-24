# Maps and Sets

## Maps
```
groovy> def map = [a:1, b:2, 'c':2] 
groovy> map.put('d', 3) 
groovy> map['e'] = 1 
groovy> map.f = 3 
groovy> println map 
[a:1, b:2, c:2, d:3, e:1, f:3]

groovy> println map.getClass().name 
java.util.LinkedHashMap
```

## LinkedList
```
groovy> def nums = [3, 1, 4, 1, 5, 9, 2, 6, 5] as LinkedList 
groovy> println nums 
groovy> println nums.class.name 
 
[3, 1, 4, 1, 5, 9, 2, 6, 5]
java.util.LinkedList
```

## Sets
```
groovy> def nums = [3, 1, 4, 1, 5, 9, 2, 6, 5] as Set 
groovy> println nums 
groovy> println nums.class.name 
 
[3, 1, 4, 5, 9, 2, 6]  // dups are removed
java.util.LinkedHashSet
```

## SortedSet
```
groovy> def nums = [3, 1, 4, 1, 5, 9, 2, 6, 5] as SortedSet 
groovy> println nums 
groovy> println nums.class.name 
 
[1, 2, 3, 4, 5, 6, 9]
java.util.TreeSet
```

## Array
```
groovy> def strings = 'this is a list of strings'.split() 
groovy> println strings 
groovy> println strings.class.name 
 
[this, is, a, list, of, strings]
[Ljava.lang.String;
```

## List - groovy try to auto convert the data type
```
groovy> List strings = 'this is a list of strings'.split() 
groovy> println strings 
groovy> println strings.class.name 
 
[this, is, a, list, of, strings]
java.util.ArrayList
```
