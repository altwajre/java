val tom = Employee("Tom", "Lee")
println(tom.firstName)

println("#polymorphism - assign subtype to abstract type")
val tomPerson:Person = tom
println(tomPerson.firstName)
