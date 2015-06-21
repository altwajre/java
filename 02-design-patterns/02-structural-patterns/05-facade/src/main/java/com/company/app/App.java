package com.company.app;

public class App
{
    static class Customer{
        public Customer(String name){ this.Name = name; }
        public String Name;
    }
    static class Bank{
        public boolean hasSufficientSavings(Customer c, int amount){
            System.out.println("Check bank for " + c.Name);
            return true;
        }
    }
    static class Credit{
        public boolean hasGoodCredit(Customer c){
            System.out.println("Check credit for " + c.Name);
            return true;
        }
    }
    static class Loan{
        public boolean hasNoBadLoans(Customer c){
            System.out.println("Check loads for " + c.Name);
            return true;
        }
    }
    static class Mortgage{
        private Bank bank = new Bank();
        private Loan loan = new Loan();
        private Credit credit = new Credit();
        public boolean isEligible(Customer customer, int amount){
            System.out.format("%s applies for %s loan\n", customer.Name, amount);
            boolean eligible = true;
            // Check creditworthyness of applicant
            if(!bank.hasSufficientSavings(customer, amount)){ eligible = false; }
            else if(!loan.hasNoBadLoans(customer)){ eligible = false; }
            else if(!credit.hasGoodCredit(customer)){ eligible = false; }
            return eligible;
        }
    }
    public static void main( String[] args )
    {
        Mortgage mortgage = new Mortgage();
        Customer customer = new Customer("Ann McKinsey");
        boolean eligible = mortgage.isEligible(customer, 125000);
        System.out.format("\n%s has been %s\n", customer.Name, (eligible ? "Approved" : "Rejected"));
    }
}
