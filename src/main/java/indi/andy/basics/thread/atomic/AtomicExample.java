package indi.andy.basics.thread.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author andy
 * b 的实现实际就是 AtomicInteger的实现  主要用到了Unsafe类直接操作内存, while自旋锁一直去比较
 * 但是 c的实现貌似也没有测试出问题, c不是volatile的,但实际unsafe.getAndAddInt 方法里面是调用了 unsafe.getIntVolatile 方法 ,可能也是保证了c的可见性
 * 可能AtomicIneger 类中的value值 加不加volatile 都能保证最终结果正确?

 */
public class AtomicExample {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static int a = 0;
    private volatile int b = 0;
    private int c = 0;

//    static Integer b = new Integer(0);
    public static void main(String[] args) throws Exception {


        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        long offset = unsafe.objectFieldOffset(AtomicExample.class.getDeclaredField("b"));
        long offset1 = unsafe.objectFieldOffset(AtomicExample.class.getDeclaredField("c"));

        AtomicExample atomicExample = new AtomicExample();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {


                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



                atomicInteger.getAndAdd(1);
                a++;
                unsafe.getAndAddInt(atomicExample, offset, 1);
                getAddInt(unsafe, atomicExample, offset1, 1);
            }
        });


        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {

                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                atomicInteger.getAndAdd(1);
                a++;
                unsafe.getAndAddInt(atomicExample, offset, 1);
                getAddInt(unsafe, atomicExample, offset1, 1);
            }
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println("a = " + a + ", b = " + atomicExample.b + ",  c=" + atomicExample.c +", at = " + atomicInteger);

        assert atomicInteger.get() == 2000;    //atomicInteger 永远是2000

        assert atomicExample.b == 2000;           //b 一定是 2000
        assert a == 2000;                      // a 不一定为 2000
        assert atomicExample.c == 2000;
    }


    public static int getAddInt(Unsafe unsafe, Object obj, long offset, int delta) {

        int v;
        do {
            v = unsafe.getInt(obj, offset);
        } while (! unsafe.compareAndSwapInt(obj, offset, v, v + delta));
        return v;
    }

/*  Unsafe底层方法实现
    public final int getAndAddInt(Object obj, long offset, int delta) {
        int v;
        do {
            //通过对象和偏移量获取变量的值
            //由于volatile的修饰, 所有线程看到的v都是一样的
            v= this.getIntVolatile(obj, offset);
    *//*
	while中的compareAndSwapInt()方法尝试修改v的值,具体地, 该方法也会通过obj和offset获取变量的值
	如果这个值和v不一样, 说明其他线程修改了obj+offset地址处的值, 此时compareAndSwapInt()返回false, 继续循环
	如果这个值和v一样, 说明没有其他线程修改obj+offset地址处的值, 此时可以将obj+offset地址处的值改为v+delta, compareAndSwapInt()返回true, 退出循环
	Unsafe类中的compareAndSwapInt()方法是原子操作, 所以compareAndSwapInt()修改obj+offset地址处的值的时候不会被其他线程中断
	*//*
        } while(!this.compareAndSwapInt(obj, offset, v, v + delta));

        return v;
    }*/
}


