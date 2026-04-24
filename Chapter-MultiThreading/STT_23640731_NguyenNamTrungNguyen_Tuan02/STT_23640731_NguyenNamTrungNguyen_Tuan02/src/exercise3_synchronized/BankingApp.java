package exercise3_synchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankingApp {
	public static BankingAccount_Synchronized account = new BankingAccount_Synchronized("John Smith");

	public static void main(String[] args) throws InterruptedException {

		Runnable depositTask = () -> {
			double amount = 100.0;
			account.deposit(amount);
		};
		
//		ExecutorService pool = Executors.newFixedThreadPool(4);
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 0; i < 1_000; i++) {
			pool.submit(depositTask);
		}
		
		pool.shutdown();
		while (!pool.isTerminated()) {
		} // wait

		System.out.println("Balance: " + account.getBalance());// 100_000 --> race condition
	}
}
