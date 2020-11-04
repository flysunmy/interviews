package indi.andy.basics.thread.multi;

import java.util.ArrayList;
import java.util.List;

public class Container3 {

    private List<Object> items = new ArrayList<>();

    public void add(Object item) {
        items.add(item);
    }

    public Object get(Integer index) {
        return items.get(index);
    }

    private Integer size() {
        return items.size();
    }

    private static Object lock = new Object();


    public static void main(String[] args) throws Exception {
        Container3 container1 = new Container3();


        new Thread(() -> {
            synchronized (lock) {
                if (container1.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程2结束size: " + container1.size());
                lock.notify();

            }

        }).start();


        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    Object o = new Object();
                    container1.add(o);

                    System.out.println("size:" + container1.size());
                    if (container1.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();


    }

}
