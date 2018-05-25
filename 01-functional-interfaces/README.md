# functional interfaces

- Predicate

Use case: A boolean expression
public interface Predicate<T>{
  boolean test(T t);
}

- Supplier

Use case: Creating objects
public interface Supplier<T>{
  T get();
}

- Consumer

Use case: Consuming from an object
public interface Consumer<T>{
  void accept(T t);
}

- Function

Use case: Select/extract from an object
public interface Function<T, R>{
  R apply(T t);
}
