package gof.behavioral.state.realworld;

/*
Definition
Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.
 */
abstract class State{  // abstract State
  protected double interest;
  protected double lowerLimit;
  protected double upperLimit;
  public Account Account;
  public double Balance;
  public abstract void deposit(double amount);
  public abstract void withdraw(double amount);
  public abstract void payInterest();
}
class RedState extends State{  // Concrete State
  private double serviceFee;
  public RedState(State state){
    this.Balance = state.Balance;
    this.Account = state.Account;
    initialize();
  }
  private void initialize(){
    // Should come from a datasource
    interest = 0.0;
    lowerLimit = -100.00;
    upperLimit = 0.0;
    serviceFee = 15.00;
  }
  @Override
  public void deposit(double amount) {
    this.Balance += amount;
    stateChangeCheck();
  }

  @Override
  public void withdraw(double amount) {
    this.Balance -= amount;
    System.out.println("No funds available for withdrawal!");

  }
  @Override
  public void payInterest() {
    // No interest is paid
  }
  private void stateChangeCheck(){
    if(this.Balance > upperLimit){
      this.Account.State = new SliverState(this);
    }
  }
}
class SliverState extends State{  // Concrete State
  public SliverState(State state){
    this(state.Balance, state.Account);
  }
  public SliverState(double balance, Account account){
    this.Balance = balance;
    this.Account = account;
    initialize();
  }
  private void initialize(){
    // Should come from a datasource
    interest = 0.0;
    lowerLimit = 0.0;
    upperLimit = 1000.0;
  }
  @Override
  public void deposit(double amount) {
    this.Balance += amount;
    stateChangeCheck();
  }
  @Override
  public void withdraw(double amount) {
    this.Balance -= amount;
    stateChangeCheck();
  }
  @Override
  public void payInterest() {
    this.Balance += interest * Balance;
    stateChangeCheck();
  }
  private void stateChangeCheck(){
    if(Balance < lowerLimit){
      this.Account.State = new RedState(this);
    }
    else if(Balance > upperLimit){
      this.Account.State = new GoldState(this);
    }
  }
}
class GoldState extends State{
  public GoldState(State state){
    this(state.Balance, state.Account);
  }
  public GoldState(double balance, Account account){
    this.Balance = balance;
    this.Account = account;
    initialize();
  }
  private void initialize(){
    interest = 0.05;
    lowerLimit = 1000.0;
    upperLimit = 10000000.0;
  }
  @Override
  public void deposit(double amount) {
    this.Balance += amount;
    stateChangeCheck();
  }
  @Override
  public void withdraw(double amount) {
    this.Balance -= amount;
    stateChangeCheck();
  }
  @Override
  public void payInterest() { this.Balance += interest * this.Balance; }
  private void stateChangeCheck(){
    if(this.Balance < 0.0){ this.Account.State = new RedState(this); }
    else if(this.Balance < lowerLimit){ this.Account.State = new SliverState(this); }
  }
}
class Account{  // Context
  private String owner;
  public State State;
  public Account(String owner){
    this.owner = owner;
    this.State = new SliverState(0.0, this);
  }
  public double getBalance(){
    return this.State.Balance;
  }
  public void deposit(double amount){
    this.State.deposit(amount);
    System.out.format("Deposited %s ---\n", amount);
    System.out.format("  Balance = %s\n", this.getBalance());
    System.out.format("  Status = %s\n", this.State.getClass().getSimpleName());
  }
  public void withdraw(double amount){
    this.State.withdraw(amount);
    System.out.format("Withdrew %s --- \n", amount);
    System.out.format("  Balance = %s\n", this.getBalance());
    System.out.format("  Status = %s\n", this.State.getClass().getSimpleName());
  }
  public void payInterest(){
    this.State.payInterest();
    System.out.println("Interest Paid --- ");
    System.out.format("  Balance = %s\n", this.getBalance());
    System.out.format("  Status = %s\n", this.State.getClass().getSimpleName());
  }
}

public class RealWorld {
  public static void main( String[] args )
  {
    Account account = new Account("Jim Johnson");
    account.deposit(500.0);
    account.deposit(300);
    account.deposit(550.0);
    account.payInterest();
    account.withdraw(2000.00);
    account.withdraw(1100.00);
  }
}
/*
Deposited 500.0 ---
  Balance = 500.0
  Status = SliverState
Deposited 300.0 ---
  Balance = 800.0
  Status = SliverState
Deposited 550.0 ---
  Balance = 1350.0
  Status = GoldState
Interest Paid ---
  Balance = 1417.5
  Status = GoldState
Withdrew 2000.0 ---
  Balance = -582.5
  Status = RedState
No funds available for withdrawal!
Withdrew 1100.0 ---
  Balance = -1682.5
  Status = RedState
 */
