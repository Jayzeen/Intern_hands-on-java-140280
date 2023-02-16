package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import exception.AmountException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Dialog Bank");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer authenticateUser() {
    System.out.println("Please enter your username");
    String username = scanner.next();

    System.out.println("Please enter your password");
    String password = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("There was an error " + e.getMessage());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;

    while (selection != 4 && customer.isAuthenticated()) {
      System.out.println("===============================");
      System.out.println("Please select one of the following options");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check balance");
      System.out.println("4: exit");
      System.out.println("===============================");

      selection = scanner.nextInt();
      double amount = 0;

      switch (selection) {
        case 1:
          System.out.println("Please enter amount to be deposited");
          amount = scanner.nextDouble();

          try {
            account.deposit(amount);

            System.out.println(amount
                + " has been deposited to account with id - "
                + account.getId());
          } catch (AmountException e) {
            System.out.println(e.getMessage());
          }
          break;

        case 2:
          System.out.println("Please enter amount to be withdrawn");
          amount = scanner.nextDouble();

          try {
            account.withdraw(amount);

            System.out.println(amount
                + " has been withdrawn from account with id - "
                + account.getId());
          } catch (AmountException e) {
            System.out.println(e.getMessage());
          }
          break;

        case 3:
          System.out.println("Checking balance for account with id - " + account.getId());
          System.out.println("===============================================");
          System.out.println("Your account balance is - Rs " + account.getBalance());
          break;

        case 4:
          System.out.println("Thank you for banking at Dialog Bank");
          Authenticator.logout(customer);
          break;

      }

    }
  }

}
