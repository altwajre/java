package com.company.app;

import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Map;


abstract class C{
    private Map<Integer, Object> customValue;
    public Map<Integer, Object> getCustomValue(){
        return customValue;
    }
    public void setCustomValue(Map<Integer, Object> customValue){
        this.customValue = customValue;
    }
}
class B extends C{
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class A{
    private B b;
    public B getB() {
        return b;
    }
    public void setB(B b){
        this.b = b;
    }
}
public class PodamTest {
    @Test
    public void test_factory_impl(){
        PodamFactory factory = new PodamFactoryImpl();
        A pojo = factory.manufacturePojo(A.class);
        Assert.assertNotNull("The class A cannot be null!", pojo);

        B b = pojo.getB();
        Assert.assertNotNull("The B object cannot be null!", b);

        Assert.assertNotNull("The Map object within B cannot be null!", b.getCustomValue());
        System.out.println(b.getName());
        System.out.println(b.getCustomValue());
    }
}
/*
output:

BAeK1Ep75M
{232849667=java.lang.Object@2aae9190, 149558653=java.lang.Object@2f333739, 1055265310=java.lang.Object@77468bd9, 1143287195=java.lang.Object@12bb4df8, -72096628=java.lang.Object@4cc77c2e}
 */