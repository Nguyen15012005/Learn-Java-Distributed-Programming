package exercise3_synchronized;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankingAccount_Synchronized {
	
	private static final double MAX_BALANCE = 100_000;
	private String owner;
	private double balance;

	public BankingAccount_Synchronized(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;
	}

	public BankingAccount_Synchronized(String owner) {
		this.owner = owner;
		this.balance = 0.0;
	}

	public synchronized void  deposit(double amount) {
		if (amount <= 0)
			 System.out.println("Amount must be positive");
	         
	        try {
	            
	            while (balance + amount > MAX_BALANCE) {
	            	System.out.println("Wait....");
	                wait();
	            }
	            balance += amount;
	            System.out.println(Thread.currentThread().getName()
	                    + " deposited " + amount + " | balance = " + balance);

	            notifyAll(); 
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	}

	public synchronized double withdraw(double amount) {
		if (amount <= 0)
			 System.out.println("Amount must be positive");
    

       try {
           while (amount > balance) {
               wait();
           }

           balance -= amount;
           System.out.println(Thread.currentThread().getName()
                   + " withdrew " + amount + " | balance = " + balance);

           notifyAll(); 
           return amount;

       } catch (Exception e) {
           e.printStackTrace();
           return 0.0;
       }
	}

	public double getBalance() {
		return balance;
	}

	public String getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "BankingAccount [owner=" + owner + ", balance=" + balance + "]";
	}

}
