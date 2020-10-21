package indi.andy.basics.reflect;


import indi.andy.structs.tree.Trie;

public class ClassExample {

    public static void main(String[] args) throws Exception{
        // classforname 会加载类到jvm,并且初始化类,执行静态代码块,初始化静态变量
//        Class<Trie> name = (Class<Trie>) Class.forName("indi.andy.structs.tree.Trie");
//        Trie o = name.newInstance();
//        System.out.println(o);
        System.out.println("------");

        // classloader 只是会加载类到jvm , 不会初始化类
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = classLoader.loadClass("indi.andy.structs.tree.Trie");
        Object o1 = aClass.newInstance();
//        System.out.println(o1);

    }

}
