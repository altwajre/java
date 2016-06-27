package com.company.app;

import java.util.Optional;

class Insurance{
    private String name;
    public Insurance(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println(getInsuranceName(new Insurance("insurance-1")));
        System.out.println(getInsuranceName(null));
    }

    static Optional<String> getInsuranceName(Insurance insurance) {
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> optInsuranceName = optInsurance.map(Insurance::getName);
        return optInsuranceName;
    }
}
/*
output:
Optional[insurance-1]
Optional.empty
 */
