class Person {
    String first
    String last

    void setLast(String last) {
        println 'inside setLast'
        this.last = last
    }
}

Person p = new Person(first: 'Tom', last: 'Lee')
println "${p.getFirst()} ${p.last}"

//output:
//inside setLast
//Tom Lee
