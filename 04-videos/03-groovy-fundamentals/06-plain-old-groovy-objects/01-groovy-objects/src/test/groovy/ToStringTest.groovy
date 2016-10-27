import org.junit.Test

class Person{
    String first
    String last
    void setLast(String last){
        println 'inside setLast()'
        this.last = last
    }
    String toString(){ "$first $last" }
}

class ToStringTest {

    @Test
    public void person_test(){
        Person p = new Person(first: 'Tom', last: 'Lee')
        println p.toString()
    }
}
