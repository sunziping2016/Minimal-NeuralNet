package CrazyWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by sun on 4/20/16.
 *
 * Crazy writer class.
 */
public class CrazyWriter {
    public static void main(String[] args) throws IOException, InterruptedException {
        final FileWriter[] writer = new FileWriter[1]; // As an holder. I know I am soooooooooo lazy. :(
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; ++i)
                        writer[0].write(String.format("%d\t%d\n", Thread.currentThread().getId(), i + 1));
                } catch (IOException error) {}
            }
        };
        Thread thread1 = new Thread(task), thread2 = new Thread(task);
        writer[0] = new FileWriter("writer.txt");
        thread1.start();
        thread2.start();
        thread2.join();
        thread1.join();
        writer[0].close();
        thread1 = new Thread(task);
        thread2 = new Thread(task);
        thread2.setPriority(8);
        writer[0] = new FileWriter("writer8.txt");
        thread1.start();
        thread2.start();
        thread2.join();
        thread1.join();
        writer[0].close();
    }
}
