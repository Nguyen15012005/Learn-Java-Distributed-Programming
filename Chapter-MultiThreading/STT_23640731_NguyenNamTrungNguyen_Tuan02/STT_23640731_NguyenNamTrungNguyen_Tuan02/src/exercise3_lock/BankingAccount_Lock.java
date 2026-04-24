package exercise3_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankingAccount_Lock {

    private static final double MAX_BALANCE = 100_000;
	private Lock lock=new ReentrantLock();
	private Condition sufficientMoney= lock.newCondition();
	private Condition belowMaxBalance = lock.newCondition();
	private String owner;
	private double balance;

	public BankingAccount_Lock(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;
	}

	public BankingAccount_Lock(String owner) {
		this.owner = owner;
		this.balance = 0.0;
	}

	public void deposit(double amount) {
		if(amount<=0)
			System.out.println("Amount must be posotive.");
		lock.lock();
		try {
			while (balance + amount > MAX_BALANCE) {
				System.out.println("MAX_BALANCE.Waith........");
                belowMaxBalance.await();
            }
			
			balance+=amount;
			System.out.println(Thread.currentThread().getName()
	                    + " deposited " + amount + " | balance = " + balance);
			sufficientMoney.signal();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}

	public double withdraw(double amount) {
		if(amount<=0)
			System.out.println("Amount must be posotive.");
		lock.lock();
		try {
			while (amount>balance) {
				System.out.println(Thread.currentThread().getName()
				        + " waiting: insufficient balance");

				System.out.println("sufficientMoney.Waith........");
				sufficientMoney.await();
			}
			balance-=amount;
			System.out.println(Thread.currentThread().getName()
	                    + " withdrew " + amount + " | balance = " + balance);
			belowMaxBalance.signalAll();
			return amount;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0.0;
		} finally {
			// TODO: handle finally clause
			lock.unlock();
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
