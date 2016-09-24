class Person {
    String first
    String last

    void setLast(String last) {
        println 'inside setLast'
        this.last = last
    }

    String toString() { "$first $last" }
}

Person p = new Person(first: 'Tom', last: 'Lee')
println p.toString()

//output:
//inside setLast
//Tom Lee
