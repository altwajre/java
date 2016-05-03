# Java 8 in Action

## Source code

> https://github.com/java8/Java8InAction

## Common functional interfaces

> Predicate<T> - T -> boolean   Use case: A boolean expression

> Supplier<T> - () -> T         Use case: Creating objects

> Consumer<T> - T -> void       Use case: Consuming from an object

> Function<T,R> - T -> R        Use case: Select/extract from an object

> Comparator<T> - (T, T) - int  Use Case: Compare two objects

### other functional interfaces

> Runnable - () -> void

> Callable<V> - () -> V

## function descriptor

```
The notation (T, U) -> R shows how to think about a function descriptor. The left side is a list types of the args. In
this case it represents a function with two args of respectively generic type T and U and that has a return type of R.
```

## Method reference

> (Apple a) -> a.getWeight() - Apple::getWeight

> () -> Thread.currentThread().dumpStack() - Thread.currentThread()::dumpStack

> (str, i) -> str.substring(i) - String::substring

> (String s) -> System.out.println(s) - System.out::println