package Exercise_01;

import java.util.concurrent.*;

public class ComputationExecutor {
    public static void main(String[] args) throws Exception {
        Callable<Long> call = new ComputationTask("long-last-computation");
        FutureTask<Long> task = new FutureTask<>(call);
        new Thread(task).start();
        long result = task.get();
        
        System.out.println("Result = " + result);

    }
}
