//SavingsAccount

public class SavingsAccount extends Account 
{
  //Additional properties/fields:
  private double interestRate;
  
  //Additional methods:
  
  //Constructs a SavingsAccount with given owner, balance, and interest rate.
  public SavingsAccount(String newOwner, double initBalance, double rate) 
  {
    super(newOwner, initBalance);
    interestRate = rate;
  }
  
  //Constructs a SavingsAccount with given owner and interest rate, with an
  //initial balance of zero, by calling THIS class' other constructor method.
  public SavingsAccount(String newOwner, double rate) 
  {
    this(newOwner, 0, rate);
  }
  
  //Additional accessors/mutators:
  public double getInterestRate() 
  {
    return interestRate;
  }
  
  public void setInterestRate(double newRate) 
  {
    interestRate = newRate;
  }
  
  //Increases the balance by an amount equal to the interestRate times the
  //current balance. Note that a 3% interest rate would be a rate of 0.03.
  public void compoundInterest() 
  {
    double y =   super.getBalance();
    y *= interestRate;
    deposit (y);
  }
  
  //Returns a String representation of the Savings Account. This can call the 
  //Account's toString method (by using super.toString()) but must add the
  //interest rate (preferably in %) to the returned value.
  public String toString() 
  {
    String result = "";
    result += super.toString();
    result += "\nInterest Rate: " + interestRate * 100 + "%";
    return result;
  }
}

