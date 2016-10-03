package com.company

class Basket {
    protected Map<Product, Integer> contents = new HashMap<>()
    void clearAllProducts(){
        contents.clear()
    }
    void addProduct(Product product){
        addProduct(product, 1)
    }
    void addProduct(Product product, int times){
        if(contents.containsKey(product)){
            int existing = contents.get(product)
            contents.put(product, existing+times)
        }
        else{
            contents.put(product, times)
        }
    }
    int getCurrentWeight(){
        int total = 0
        for(Map.Entry<Product, Integer> entry : contents.entrySet()){
            total = total + (entry.getKey().getWeight() * entry.getValue())
        }
        return total
    }
    int getProductTypesCount(){
        return contents.size()
    }
}
