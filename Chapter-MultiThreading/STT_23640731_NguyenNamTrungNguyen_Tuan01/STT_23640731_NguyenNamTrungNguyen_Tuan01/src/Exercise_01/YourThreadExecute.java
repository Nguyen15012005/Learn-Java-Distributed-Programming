package Exercise_01;

public class YourThreadExecute {
    public static void main(String[] args) {
        YourThread t1 = new YourThread("Print Task", 20);
        YourThread t2 = new YourThread("Distribute Task", 23);

        t1.start();
        t2.start();
    }
}
