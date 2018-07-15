import org.junit.Test

class Department{
    Manager boss
}
class Manager{
    String name
}
class SafeNavigationTest {
    @Test
    public void null_check_test(){
        def d = new Department(boss: new Manager(name: "Tom"))
        println d.boss.name
        d = new Department()
        println d?.boss?.name
    }
    /*
    output:
    Tom
    null
     */
}
