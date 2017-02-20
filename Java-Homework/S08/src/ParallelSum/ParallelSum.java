package ParallelSum;

import java.util.Random;

/**
 * Created by sun on 4/20/16.
 *
 * Parallel sum class.
 */
public class ParallelSum {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        final int[] array = new int[300], sum = new int[1];
        for (int i = 0; i < array.length; ++i)
            array[i] = random.nextInt(100) + 1;
        class Task implements Runnable {
            public Task(int start, int end) {
                this.start = start;
                this.end = end;
            }
            private int start, end;
            @Override
            public void run() {
                int partialsum = 0;
                for (int i = start; i < end; ++i)
                    partialsum += array[i];
                synchronized (sum) {
                    sum[0] += partialsum;
                }
            }
        }
        Thread[] threads = new Thread[] {
                new Thread(new Task(0, 100)),
                new Thread(new Task(100, 200)),
                new Thread(new Task(200, 300)),
        };
        for (Thread i: threads)
            i.start();
        for (Thread i: threads)
            i.join();
        System.out.println(sum[0]);
        /*int check = 0;
        for (int i: array)
            check += i;
        System.out.println(check);*/
    }
}
