package bank;

import exception.AmountException;

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

  public void deposit(double amount) throws AmountException {
    double initial_balance = getBalance();
    double new_balance = initial_balance + amount;

    if (amount <= 0) {
      throw new AmountException("Amount should be positive");
    } else {
      setBalance(new_balance);
      DataSource.updateAccountBalance(id, new_balance);
    }

  }

  public void withdraw(double amount) throws AmountException {
    double initial_balance = getBalance();
    double new_balance = 0;

    if (amount >= initial_balance) {
      throw new AmountException("Insufficient Funds");
    } else {
      new_balance = initial_balance - amount;
      setBalance(new_balance);
      DataSource.updateAccountBalance(id, new_balance);
    }
  }

}
