# Iterating Using Closures

## .each with it dummy default
```
groovy> List nums = [3, 1, 4, 1, 5, 9] 
groovy> nums.each { println it } 
3
1
4
1
5
9
Result: [3, 1, 4, 1, 5, 9]
```

## .each with custom variable name
```
groovy> List nums = [3, 1, 4, 1, 5, 9] 
groovy> nums.each { n -> println n } 
 
3
1
4
1
5
9
Result: [3, 1, 4, 1, 5, 
```

## .eachWithIndex
```
groovy> List nums = [3, 1, 4, 1, 5, 9] 
groovy> nums.each { n -> println n } 
groovy> nums.eachWithIndex { n, idx -> println "nums[$idx] == $n" } 
 
3
1
4
1
5
9
nums[0] == 3
nums[1] == 1
nums[2] == 4
nums[3] == 1
nums[4] == 5
nums[5] == 9
Result: [3, 1, 4, 1, 5, 9]
```