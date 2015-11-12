package com.company.app;

public class App
{
    static class Earth{
        public Integer size = 8;
    }
    static class Atlas{
        public final Earth earth;  // immutable field
        public Atlas(Earth earth){
            this.earth = earth;
//            this.earth = null;  compile error
        }
        public void reset(){
//            earth = new Earth();  compile error
        }
        public void changeSize(){
            this.earth.size = 18;  // it is ok to change immutable field's property
        }
    }
    public static void main( String[] args )
    {
        Atlas atlas = new Atlas(new Earth());
        System.out.println(atlas.earth.size);
        atlas.changeSize();  // change immutable field's property
        System.out.println(atlas.earth.size);
    }
}
