package bank;

public class Account {

  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws IllegalStateException {
    double initial_balance = getBalance();
    
    if (amount <= 0) {
      throw new IllegalStateException("Amount should be positive");
    } else {
      setBalance(initial_balance + amount);
    }

  }

  public void withdraw(double amount) throws IllegalStateException {
    double initial_balance = getBalance();

    if (amount >= initial_balance) {
      throw new IllegalStateException("Insufficient Funds");
    } else {
      setBalance(initial_balance - amount);
    }
  }

}
