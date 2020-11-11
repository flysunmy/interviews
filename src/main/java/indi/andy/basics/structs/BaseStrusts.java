package indi.andy.basics.structs;

import java.util.ArrayList;
import java.util.HashSet;

public class BaseStrusts {

    public static void main(String[] args){
        Integer a = 2; //   自动装箱 实际调用了 a = Integer.valueOf(2) 先从常量池里获取默认[-128, 127],上限制可配, 若获取不到就new Integer()
        int b = a;     //   自动拆箱 实际调用了 b = a.intValue()

        Integer c = 2;
        Integer d = new Integer(2);
        Integer e = new Integer(2);
        System.out.println(a == c);  // true
        System.out.println(c == d);  // false
        System.out.println(d == e);  // false


        int i = 2;
        int j = 2;
        System.out.println(i == j);   // true

        long l = 2L;
        long k = 2;

        System.out.println(k == i);   // true

        int m = (int) l;
        System.out.println(m);


        ArrayList<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(3);
        list.add(4);
        list.add(2);
        System.out.println(list);

        HashSet<Integer> integers = new HashSet<>();
        integers.add(9);
        integers.add(3);
        integers.add(4);
        integers.add(2);
        System.out.println(integers);
    }
}

