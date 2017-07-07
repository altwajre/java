package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Apple {
  private Integer weight = 0;
  private String color;

  public boolean isTrue() {
    return false;
  }

  public boolean isHeavy(Apple apple) {
    return apple.getWeight() > 150;
  }

  public static boolean isStaticHeavy(Apple apple) {
    return apple.getWeight() > 150;
  }
}

public class App {
  public static void main(String[] args) {

    constructorReference();

    staticMethod();

    instanceMethodOfType();

    instanceMethodOfAnObject();

  }

  private static void constructorReference() {
    System.out.println("#constructorReference: Apple::new");
    Apple apple = new Apple();
    System.out.println("regular: weight="+apple.getWeight());

    Supplier<Apple> lambdaSupplier = () -> new Apple();
    Apple lambdaApple = lambdaSupplier.get();
    System.out.println("lambda: weight="+lambdaApple.getWeight());

    Supplier<Apple> cRSupplier = Apple::new;
    Apple crApple = cRSupplier.get();
    System.out.println("ctor reference: weight="+crApple.getWeight());
  }

  private static void instanceMethodOfAnObject() {
    System.out.println("#instanceMethodOfAnObject: new Apple()::isHeavy");
    Predicate<Apple> lambdaPredicate = (Apple apple) -> apple.getWeight() > 150;
    boolean lambdaResult = lambdaPredicate.test(new Apple(180, "red"));
    System.out.println(lambdaResult);

    Predicate<Apple> mrPredicate = new Apple()::isHeavy;
    boolean mrResult = mrPredicate.test(new Apple(168, "green"));
    System.out.println("method reference: boolean isHeavy(Apple apple)" + mrResult);

    Supplier<Boolean> mrSupplier = new Apple()::isTrue;
    System.out.println("method reference: boolean isTrue()"+mrSupplier.get());
  }

  private static void instanceMethodOfType() {
    System.out.println("#instanceMethodOfType: Apple::isTrue");
    Predicate<Apple> lambdaPredicate = (Apple apple) -> apple.isTrue();
    boolean lambdaResult = lambdaPredicate.test(new Apple());
    System.out.println("lambda: " + lambdaResult);

    Predicate<Apple> mrPredicate = Apple::isTrue;
    boolean mrResult = mrPredicate.test(new Apple());
    System.out.println("method reference: " + mrResult);
  }

  private static void staticMethod() {
    System.out.println("#staticMethod: Apple.isStaticHeavy()");
    boolean heavy = Apple.isStaticHeavy(new Apple(180, "red"));
    System.out.println("regular: heavy=" + heavy);

    Predicate<Apple> predicate = Apple::isStaticHeavy;
    boolean mrHeavy = predicate.test(new Apple(168, "green"));
    System.out.println("method reference: heavy=" + mrHeavy);
  }
}
/*
output:
#constructorReference: Apple::new
regular: weight=0
lambda: weight=0
ctor reference: weight=0
#staticMethod: Apple.isStaticHeavy()
regular: heavy=true
method reference: heavy=true
#instanceMethodOfType: Apple::isTrue
lambda: false
method reference: false
#instanceMethodOfAnObject: new Apple()::isHeavy
true
method reference: boolean isHeavy(Apple apple)true
method reference: boolean isTrue()false
 */
