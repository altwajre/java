// trim the assert statement into a shorter form instead of converting it explicitly to Boolean variables

assert true
assert !false

assert true || false
assert true && !false

String name = "Tom"
assert name

String empty = ""
assert !empty

class Person{
    String name;
}

Person person = new Person()
assert person

Person nullRef = null
assert !nullRef

int age = 18
assert age

int zero = 0
assert !zero

Object[] array = new Object[3]
assert array

Object[] emptyArray = new Object[0]
assert !emptyArray

def myRegex = ~/needle/
assert myRegex.matcher("needle in haystack")
assert !myRegex.matcher("Wrong haystack")

println "Scripts has finished because all asserts pass"
