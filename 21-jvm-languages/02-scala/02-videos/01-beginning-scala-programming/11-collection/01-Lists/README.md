# Lists

- Lists are immutable collections, duplicates allowed, access by index, searchable
- Nil is an empty List
- Lists can also be created from the companion object, and the apply method
- Lists have many of the same functional properties as other collections

```
scala> 1 :: 2 :: 3 :: 4 :: 5 :: Nil
res3: List[Int] = List(1, 2, 3, 4, 5)

scala> 5 :: Nil
res5: List[Int] = List(5)
```
