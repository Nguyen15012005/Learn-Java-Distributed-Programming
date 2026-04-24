package Exercise_01;

public class DisplayOneToTenExecutor {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DisplayOneToTenTask(), "Thread-1");
        Thread t2 = new Thread(new DisplayOneToTenTask(), "Thread-2");

        t1.start();
        t2.start();
    }
}

