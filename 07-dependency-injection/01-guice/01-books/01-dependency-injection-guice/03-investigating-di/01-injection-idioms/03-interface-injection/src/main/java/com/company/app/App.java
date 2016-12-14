package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;

class Phaser{};
class Starship implements PhaserMountable{
    private Phaser phaser;
    // method below is called by the injector
    public void mount(Phaser phaser) {
        System.out.println("#Starship.mount()");
        this.phaser = phaser;
    }
}
interface PhaserMountable{
    void mount(Phaser phaser);
}
class MyModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(PhaserMountable.class).to(Starship.class);
    }
}
class PhaserMountableClient{
    private PhaserMountable phaserMountable;
    @Inject
    public PhaserMountableClient(PhaserMountable phaserMountable){
        this.phaserMountable = phaserMountable;
    }
    public void log(){
        System.out.println("#" + phaserMountable.getClass().getSimpleName());
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new MyModule()).getInstance(PhaserMountable.class).mount(new Phaser());
        Guice.createInjector(new MyModule()).getInstance(PhaserMountableClient.class).log();
    }
}
/*
output:
#Starship.mount()
#Starship
 */
