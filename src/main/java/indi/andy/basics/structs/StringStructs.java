package indi.andy.basics.structs;
public class StringStructs {

    public static void main(String[] args){
        String a = "a";
        String b = "a";
        System.out.println(a == b);       //false
        System.out.println(a.equals(b));  //true

        String c = new String("a");
        System.out.println(a == c);
    }
}
