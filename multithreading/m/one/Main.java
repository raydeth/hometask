import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private volatile static Integer ITERATOR = 0;

    public static void main(String[] args) {
//        final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        final Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
//        final Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<Integer, Integer>());
        final ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<Integer, Integer>(new HashMap<Integer, Integer>());

        final Scanner in = new Scanner(System.in);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int value = in.nextInt();
                    map.put(ITERATOR++, value);
                    synchronized (Main.class) {
                        Main.class.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (Main.class) {
                        try {
                            Main.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int sum = 0;
                    synchronized (map) {
                        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
                            if (sum == 0) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            sum += integerIntegerEntry.getValue();
                        }
                    }
                    System.out.println("Total sum: " + sum);
                }
            }
        }).start();


    }
}
