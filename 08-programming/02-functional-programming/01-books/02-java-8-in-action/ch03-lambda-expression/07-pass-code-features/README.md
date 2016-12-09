# Features so far

## behavior parameterization

```
class AppleComparator implements Comparator<Apple>{
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
inventory.sort(new AppleComparator()); // passing code
```

## anonymous class

```
inventory.sort(new Comparator<Apple>() { // anonymous class
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
});
```

## lambda expression

### basic

```
inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo((a2.getWeight())));
```

### infer type

```
inventory.sort((a1, a2) -> a1.getWeight().compareTo((a2.getWeight())));
```

### static method

```
/*
    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
            (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }
 */
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
        inventory.sort(c);
//        inventory.sort(Comparator.comparing((a) -> a.getWeight())); // or a more compact form
```

### method reference

```
class Apple{
    public static int compare(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeight());
    }
}
inventory.sort(Apple::compare);
```

