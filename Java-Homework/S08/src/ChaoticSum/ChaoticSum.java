package ChaoticSum;

/**
 * Created by sun on 4/20/16.
 *
 * Chaotic sum class.
 */
public class ChaoticSum {
    public static void main(String[] args) throws InterruptedException {
        final int[] sum = new int[1];
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; ++i) {
                        int t = sum[0];
                        t += 1;
                        Thread.sleep(1);
                        sum[0] = t;
                    }
                } catch (InterruptedException error) {}
            }
        });
        t.start();
        for (int i = 0; i < 20; ++i) {
            sum[0] += 1;
            Thread.sleep(1);
        }
        t.join();
        System.out.println(sum[0]);
    }
}
