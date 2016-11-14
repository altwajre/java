# Groovy Ranges and Lists

## Ranges

```
groovy> Range r = 1..10 
groovy> println(r) 
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

groovy> println r.from 
1

groovy> println r.to
10

groovy> println r.contains(2) 
true

groovy> r = 1..<10 
groovy> println(r) 
[1, 2, 3, 4, 5, 6, 7, 8, 9]
```

## Lists

```
groovy> List nums = [3, 1, 4, 1, 5, 9, 2, 6, 5] 
groovy> println nums 
[3, 1, 4, 1, 5, 9, 2, 6, 5]

groovy> println nums.class.name 
java.util.ArrayList

groovy> println nums.drop(3) // drop the first 3 
[1, 5, 9, 2, 6, 5]

groovy> println nums[1..3] 
[1, 4, 1]

groovy> println nums - 1 - 5 // remove 1s and 5s 
[3, 4, 9, 2, 6]

groovy> println nums << [3, 5] 
[3, 1, 4, 1, 5, 9, 2, 6, 5, [3, 5]]

groovy> [3, [1, 4], 8].flatten() 
Result: [3, 1, 4, 8]
```
