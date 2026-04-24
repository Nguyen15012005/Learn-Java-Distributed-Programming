package exercise1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask3 extends RecursiveTask<Long> {
    private long start, end, threshold;

    public SumTask3(long start, long end, long threshold) {
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {
        if (end - start <= threshold) {
            long total = 0L;
            for (long i = start; i < end; i++) {
                total += i;
            }
            return total;
        }

        long mid = (start + end) / 2;
        SumTask3 leftTask = new SumTask3(start, mid, threshold);
        SumTask3 rightTask = new SumTask3(mid, end, threshold);

        leftTask.fork(); 
        long resultRight = rightTask.compute(); 
        long resultLeft = leftTask.join();

        return resultLeft + resultRight;
    }
}

public class SumRangeParallel {
    public static void main(String[] args) {
        long range = 1_000_000L;
        long threshold = 100_000L;

        ForkJoinPool pool = new ForkJoinPool();
        long total = pool.invoke(new SumTask3(0, range, threshold));

        System.out.println("Total (Parallel): " + total);
    }
}