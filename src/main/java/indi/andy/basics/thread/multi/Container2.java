package indi.andy.basics.thread.multi;

import java.util.ArrayList;
import java.util.List;

public class Container2 {

    private  volatile List<Object> items = new ArrayList<>();

    public void add(Object item) {
        items.add(item);
    }

    public Object get(Integer index) {
        return items.get(index);
    }


    private Integer size() {
        return items.size();
    }


    public static void main(String[] args) throws Exception {
        Container2 container1 = new Container2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Object o = new Object();
                container1.add(o);
                System.out.println("size:" + container1.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();


        new Thread(() -> {
            while (true) {
                if (container1.size() == 5) {
                    break;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2结束");
        }).start();

    }

}
