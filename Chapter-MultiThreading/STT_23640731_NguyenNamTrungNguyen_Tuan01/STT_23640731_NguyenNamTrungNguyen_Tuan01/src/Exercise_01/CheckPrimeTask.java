package Exercise_01;

public class CheckPrimeTask implements Runnable {
    private int x;

    public CheckPrimeTask(int x) {
        this.x = x;
    }

    @Override
    public void run() {
        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                isPrime = false;
                break;
            }
        }

        System.out.println(x + " is prime? " + isPrime);
    }
}
