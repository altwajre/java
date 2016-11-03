package com.company.app;

class Purchase{  // Class that holds request details
    public Purchase(int number, double amount, String purpose){
        this.Number = number;
        this.Amount = amount;
        this.Purpose = purpose;
    }
    public double Amount;
    public String Purpose;
    public int Number;
}
abstract class Approver{  // abstract Handler
    protected Approver successor;
    public void setSuccessor(Approver successor){ this.successor = successor; }
    public abstract void processRequest(Purchase purchase);
}
class Director extends Approver{  // Concrete Handler
    @Override
    public void processRequest(Purchase purchase) {
        if(purchase.Amount < 10000.0){
            System.out.format("%s approved request# %s\n", this.getClass().getSimpleName(), purchase.Number);
        }
        else if(successor != null){ successor.processRequest(purchase); }
    }
}
class VicePresident extends Approver{  // Concrete Handler
    @Override
    public void processRequest(Purchase purchase) {
        if(purchase.Amount < 25000.0){
            System.out.format("%s approved request# %s\n", this.getClass().getSimpleName(), purchase.Number);
        }
        else if(successor != null){ successor.processRequest(purchase); }
    }
}
class President extends Approver{  // Concrete Handler
    @Override
    public void processRequest(Purchase purchase) {
        if(purchase.Amount < 100000.0){
            System.out.format("%s approved request# %s\n", this.getClass().getSimpleName(), purchase.Number);
        }
        else{ System.out.format("Request# %s requires an executive meeting!", purchase.Number); }
    }
}
public class App
{
    public static void main( String[] args )
    {
        // Setup Chain of Responsibility
        Approver larry = new Director();
        Approver sam = new VicePresident();
        Approver tammy = new President();
        larry.setSuccessor(sam);
        sam.setSuccessor(tammy);
        // Generate and process purchase requests
        Purchase p = new Purchase(2034, 350.00, "Supplies");
        larry.processRequest(p);
        p = new Purchase(2035, 32590.10, "Project X");
        larry.processRequest(p);
        p = new Purchase(2036, 122100.00, "Project Y");
        larry.processRequest(p);
    }
}
/*
Definition
Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request.
Chain the receiving objects and pass the request along the chain until an object handles it.

output:
Director approved request# 2034
President approved request# 2035
Request# 2036 requires an executive meeting!
 */