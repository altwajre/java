# Method references

```
Method references let you reuse existing method definitions and pass them just like lambdas.
```

## lambda and method reference equivalents

> (Apple a) -> a.getWeight() - Apple::getWeight  <- instance method of Type

> () -> Thread.currentThread().dumpStack() - Thread.currentThread()::dumpStack  <- static method

> (str, i) -> str.substring(i) - String::substring  <- instance method of Type

> (String s) -> System.out.println(s) - System.out::println  <- instance method of Type
