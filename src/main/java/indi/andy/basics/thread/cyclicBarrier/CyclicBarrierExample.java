package indi.andy.basics.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println(Thread.currentThread().getName() + " ========"));




        new Thread(new Worker("A", 3, barrier), "Thread-A").start();
        new Thread(new Worker("B", 3, barrier), "Thread-B").start();
        new Thread(new Worker("C", 3, barrier), "Thread-C").start();
    }
}

class Worker implements Runnable {
    private String key;

    private int times;

    private CyclicBarrier cyclicBarrier;

    public Worker(String key, int times, CyclicBarrier cyclicBarrier) {
        this.key = key;
        this.times = times;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println(Thread.currentThread().getName() + " " + key);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}


