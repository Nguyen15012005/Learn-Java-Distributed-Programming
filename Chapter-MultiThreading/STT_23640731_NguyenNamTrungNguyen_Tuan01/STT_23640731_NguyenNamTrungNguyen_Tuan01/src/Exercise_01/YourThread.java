package Exercise_01;

public class YourThread extends Thread {
    private String taskName;
    private int counter;

    public YourThread(String taskName, int counter) {
        this.taskName = taskName;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            System.out.println(taskName + "#" + i);
        }
    }
}
