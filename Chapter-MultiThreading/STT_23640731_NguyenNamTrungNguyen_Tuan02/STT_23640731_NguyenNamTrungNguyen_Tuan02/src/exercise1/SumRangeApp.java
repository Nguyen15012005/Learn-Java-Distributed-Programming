package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Callable + ExecuteService
public class SumRangeApp {
	public static void main(String[] args) throws InterruptedException {
        long range = 1_000_000L;
        int numTasks = 10;
        long subRange = range / numTasks;

        List<SumRange> tasks = new ArrayList<>();

        for (int i = 0; i < numTasks; i++) {
            long start = i * subRange;
            long end = (i == numTasks - 1 ? range : start + subRange);
            tasks.add(new SumRange(start, end));
        }

        ExecutorService pool = Executors.newCachedThreadPool();
        
        List<Future<Long>> results = pool.invokeAll(tasks);

        long total = results.stream()
                .mapToLong(r -> {
                    try {
                        return r.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return 0L;
                    }
                })
                .sum();

        System.out.println("Total: " + total);
        
        pool.shutdown();
    }
}
