package indi.andy.basics.thread.semaphore;

import java.util.concurrent.Semaphore;

/***
 *
 * @Description : 三个线程循环打印 A B C
 * @author : wuzichao
 * @CreateDate : 2020-11-09 10:49:46
 *
 */
public class SemaphoreExample {

    public static void main(String[] args) throws InterruptedException {
        Semaphore A = new Semaphore(1);
        Semaphore B = new Semaphore(0);
        Semaphore C = new Semaphore(0);

        new Thread(new Worker("A", 3, A, B), "Thread-A").start();
        new Thread(new Worker("B", 3, B, C), "Thread-B").start();
        new Thread(new Worker("C", 3, C, A), "Thread-C").start();

    }


}


class Worker implements Runnable {
    private String key;
    private int times;
    private Semaphore current;
    private Semaphore next;

    public Worker(String key, int times, Semaphore current, Semaphore next) {
        this.key = key;
        this.times = times;
        this.current = current;
        this.next = next;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {

            try {
                current.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " " + key);
            next.release();

        }
    }
}
