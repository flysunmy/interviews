package indi.andy.basics.thread.multi;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExample {
    static AtomicInteger a = new AtomicInteger(0);
    static int b = 1;
    int c;

    public static void main(String[] args) throws Exception {

        Class<?> aClass = ThreadExample.class.getClassLoader().loadClass("sun.misc.Unsafe");
        Field theUnsafe = aClass.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(aClass);
        System.out.println(unsafe);
        Worker worker = (Worker) unsafe.allocateInstance(Worker.class);
        System.out.println(worker);
        new Thread(worker).start();

    }
}

class Worker implements Runnable {
    private CountDownLatch latch;

    private String word;

    private Integer times;

    public Worker(CountDownLatch latch, String word, Integer times) {
        this.latch = latch;
        this.word = word;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + word);
        }
    }
}
