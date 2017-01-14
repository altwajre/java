# Groovy features useful to spock tests

Using map-based constructors for object creation
```
new Employee(age: 22, firstName: "Alice", lastName: "Olson", inTraining: true)
new Employee(middleName: "Jones", lastName: "Corwin", age: 45, firstName: "Alex")
```

Maps and lists
```
Map<String, Integer> wordCounts = ["Hello": 1, "Groovy": 1, "World": 2]
List<String> groovyRaces = ["Drazi", "Minbari", "Humans"]
```

