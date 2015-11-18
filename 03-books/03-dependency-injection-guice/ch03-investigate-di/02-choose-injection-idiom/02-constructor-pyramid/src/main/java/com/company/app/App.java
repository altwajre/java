package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

class Gills{}
class Lungs{}
class Heart{}
class Amphibian{
    private Gills gills;
    private Lungs lungs;
    private Heart heart;
    @Inject
    public Amphibian(Heart heart, Gills gills){
        this.heart = heart;
        this.gills = gills;
    }
    @Inject
    public Amphibian(Heart heart, Lungs lungs){
        this.heart = heart;
        this.lungs = lungs;
    }
    public void log(){
        if(gills != null){
            System.out.println("water");
        }
        if(lungs != null){
            System.out.println("lungs");
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Amphibian.class).log();
    }
}
/*
# 3.2.2. The constructor pyramid problem

output:
Exception in thread "main" com.google.inject.ConfigurationException: Guice configuration errors:

1) com.company.app.App$Amphibian has more than one constructor annotated with @Inject. Classes must have either one (and only one) constructor annotated with @Inject or a zero-argument constructor that is not private.
  at com.company.app.App$Amphibian.class(App.java:16)
  while locating com.company.app.App$Amphibian
 */
