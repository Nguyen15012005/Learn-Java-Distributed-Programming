package Exercise_01;

public class CheckPrimeExecutor {
    public static void main(String[] args) {
        Thread t1 = new Thread(new CheckPrimeTask(17));
        Thread t2 = new Thread(new CheckPrimeTask(25));
        Thread t3 = new Thread(new CheckPrimeTask(29));

        t1.start();
        t2.start();
        t3.start();
    }
}
