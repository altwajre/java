package com.company.app;

/*
Builder pattern
Instead of making the desired object directly, the client calls a constructor (or static factory) with all of the
required parameters and gets a builder object. Then the client calls setter-like methods on the builder and gets a
builder object to set each optional parameter of interest. Finally, the client calls a parameterless build method to
generate the object, which is immutable.
 */
class NutritionFacts{
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;
    public static class Builder{
        // Required parameters
        private final int servingSize;
        private final int servings;
        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int carbohydrate = 0;
        private int sodium = 0;
        public Builder(int servingSize, int servings){ // a builder constructor with all of the required parameters
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val){ calories = val; return this; }
        public Builder fat(int val){ fat = val; return this; } // setter-like methods to get builder object to set optional parameter
        public Builder carbohydrate(int val){ carbohydrate = val; return this; }
        public Builder sodium(int val){ sodium = val; return this; }
        public NutritionFacts build(){ // parameterless build method that generates the object which is immutable
            return new NutritionFacts(this);
        }
    }
    private NutritionFacts(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
    public int getServingSize(){
        return servingSize;
    }
}
public class App
{
    public static void main( String[] args )
    {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(cocaCola.getServingSize());
    }
}
/*
output:
240
 */
