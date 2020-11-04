package indi.andy.basics.thread.multi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Container4 {

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

    private static CountDownLatch latch = new CountDownLatch(1);


    public static void main(String[] args) throws Exception {
        Container4 container1 = new Container4();


        new Thread(() -> {
            try {
                latch.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程2结束");

        }).start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Object o = new Object();
                container1.add(o);

                System.out.println("size:" + container1.size());
                if (container1.size() == 5) {
                    latch.countDown();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }).start();


    }

}
