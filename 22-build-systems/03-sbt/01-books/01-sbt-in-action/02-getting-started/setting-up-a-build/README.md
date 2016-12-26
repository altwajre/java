# Setting up a build

## Create project

```
$ mkdir preowned-kittens
$ cd preowned-kittens
$ nano build.sbt
name := "preowned-kittens"

version := "1.0"
$ nano project/build.properties
sbt.version=0.13.13
```

## Add runnable (main()) and run it

```
$ mkdir -p src/main/scala
$ nano src/main/scala/PreownedKittenMain.scala
object PreownedKittenMain extends App {
  println("Hello, sbt world!")
}

$ sbt
> compile
[info] Compiling 1 Scala source to /Users/
[success] Total time: 1 s, completed Dec 26, 2016 11:35:47 AM
> run
[info] Running PreownedKittenMain 
Hello, sbt world!
[success] Total time: 0 s, completed Dec 26, 2016 11:35:57 AM
```

## Add models and experiment it in REPL 

```
$ nano src/main/scala/models.scala
case class Kitten(id: Long, attributes: Seq[String])
case class BuyerPreferences(attributes: Seq[String])

> console
[info] Compiling 1 Scala source to 
[info] Starting scala interpreter...
[info] 
Welcome to Scala version 2.10.6 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_45).
Type in expressions to have them evaluated.
Type :help for more information.

scala> val tabby = Kitten(id=124, attributes=Seq("female","tabby","kid-friendly"))
tabby: Kitten = Kitten(124,List(female, tabby, kid-friendly))

scala> val prefs = BuyerPreferences(List("male", "tabby", "kid-friendly"))
prefs: BuyerPreferences = BuyerPreferences(List(male, tabby, kid-friendly))

scala> prefs.attributes.map(attribute => tabby.attributes.contains(attribute))
res1: Seq[Boolean] = List(false, true, true)

scala> res1 map (matched => if(matched) 1.0 else 0)
res2: Seq[Double] = List(0.0, 1.0, 1.0)

scala> res2.sum / res2.length
res3: Double = 0.6666666666666666

scala> exit

> exit
```

## Add logic and experiment it in REPL

```
$ nano src/main/scala/logic.scala
$ cat src/main/scala/logic.scala
object Logic {
  def matchLikelihood(kitten: Kitten, buyer: BuyerPreferences): Double = {
    val matches = buyer.attributes map { attribute =>
      kitten.attributes contains attribute
    }
    val nums = matches map { b => if(b) 1.0 else 0.0 }
    nums.sum / nums.length
  }
}

$ sbt
[info] Loading project definition from 
[info] Set current project to preowned-kittens (in build file:)
> console
[info] Compiling 3 Scala sources to 
[info] Starting scala interpreter...
[info] 
Welcome to Scala version 2.10.6 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_45).
Type in expressions to have them evaluated.
Type :help for more information.

scala> val tabby = Kitten(id=124, attributes=Seq("female","tabby","kid-friendly"))
tabby: Kitten = Kitten(124,List(female, tabby, kid-friendly))

scala> val prefs = BuyerPreferences(List("male", "tabby", "kid-friendly"))
prefs: BuyerPreferences = BuyerPreferences(List(male, tabby, kid-friendly))

scala> Logic.matchLikelihood(tabby, prefs)
res0: Double = 0.6666666666666666
```

## Testing

**Add Test dependency to build.sbt**

```
libraryDependencies += "org.specs2" % "specs2_2.10" % "1.14" % "test"
```

**Reload Project**

```
> reload
[info] Loading project definition from 
[info] Set current project to preowned-kittens (in build file:)
```

**Add Test and Run Test**

```
$ mkdir -p src/test/scala
$ nano src/test/scala/LogicSpec.scala
import org.specs2.mutable.Specification

object LogicSpec extends Specification {
  "The 'matchLikelihood' method" should {
    "be 100% when all attribute match" in {
      val tabby = Kitten(1, List("male", "tabby"))
      val prefs = BuyerPreferences(List("male", "tabby"))
      val result = Logic.matchLikelihood(tabby, prefs)
      result must beGreaterThan(.999)
    }
  }
}

> test
[info] LogicSpec
[info] 
[info] The 'matchLikelihood' method should
[info] + be 100% when all attribute match
[info]  
[info] Total for specification LogicSpec
[info] Finished in 13 ms
[info] 1 example, 0 failure, 0 error
[info] 
[info] Passed: Total 1, Failed 0, Errors 0, Passed 1
[success] Total time: 1 s, completed Dec 26, 2016 3:21:37 PM
```

**Test in Watch Mode - auto detect changes and rerun test**

```
> ~test
[info] LogicSpec
[info] 
[info] The 'matchLikelihood' method should
[info] + be 100% when all attribute match
[info]  
[info]  
[info] Total for specification LogicSpec
[info] Finished in 12 ms
[info] 1 example, 0 failure, 0 error
[info] 
[info] Passed: Total 1, Failed 0, Errors 0, Passed 1
[success] Total time: 1 s, completed Dec 26, 2016 3:29:38 PM
1. Waiting for source changes... (press enter to interrupt)
```

Add following test to LogicSpec.scala

```
  "The 'matchLikelihood' method" should {
    "be 0% when no attributes match" in {
      val tabby = Kitten(1, List("male", "tabby"))
      val prefs = BuyerPreferences(List("female", "calico"))
      val result = Logic.matchLikelihood(tabby, prefs)
      result must beLessThan(.001)
    }
  }
```

Tests are rerun after sbt detected the file changed

```
2. Waiting for source changes... (press enter to interrupt)
[info] Compiling 1 Scala source to /Users/whan/Desktop/java/22-build-systems/03-sbt/01-books/01-sbt-in-action/02-getting-started/setting-up-a-build/preowned-kittens/target/scala-2.10/test-classes...
[info] LogicSpec
[info] 
[info] The 'matchLikelihood' method should
[info] + be 100% when all attribute match
[info]  
[info] The 'matchLikelihood' method should
[info] + be 0% when no attributes match
[info]  
[info]  
[info] Total for specification LogicSpec
[info] Finished in 14 ms
[info] 2 examples, 0 failure, 0 error
[info] 
[info] Passed: Total 2, Failed 0, Errors 0, Passed 2
[success] Total time: 2 s, completed Dec 26, 2016 3:30:31 PM
```
