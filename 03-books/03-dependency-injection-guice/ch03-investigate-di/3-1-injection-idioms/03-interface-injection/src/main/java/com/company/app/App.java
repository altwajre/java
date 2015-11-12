package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;

public class App
{
    static class Phaser{};
    static class Starship implements PhaserMountable{
        private Phaser phaser;
        // method below is called by the injector
        public void mount(Phaser phaser) {
            System.out.println("#Starship.mount()");
            this.phaser = phaser;
        }
    }
    public interface PhaserMountable{
        void mount(Phaser phaser);
    }
    static class MyModule extends AbstractModule{
        @Override
        protected void configure() {
            bind(PhaserMountable.class).to(Starship.class);
        }
    }
    static class PhaserMountableClient{
        private PhaserMountable phaserMountable;
        @Inject
        public PhaserMountableClient(PhaserMountable phaserMountable){
            this.phaserMountable = phaserMountable;
        }
        public void log(){
            System.out.println("#" + phaserMountable.getClass().getSimpleName());
        }
    }

    /*
    output:
    #Starship.mount()
    #Starship
     */
    public static void main( String[] args )
    {
        Guice.createInjector(new MyModule()).getInstance(PhaserMountable.class).mount(new Phaser());
        Guice.createInjector(new MyModule()).getInstance(PhaserMountableClient.class).log();
    }
}
