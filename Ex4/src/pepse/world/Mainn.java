package pepse.world;//package pepse.world;
//
//import java.util.*;
//
////class pepse.world.main {
////    public <T, E extends T> void foo(E e, T t) {
////        LinkedList<?> list = new LinkedList<T>();
////        list.add(null);
////    }
////}
//
//interface A {
//    int na = 7;
//    int x = 4;
//    private void foooo() {
//         int y = 9;
//    }
//    default <? extends B> foo1(List<?> no){
//        System.out.println("hi");
//    }
//}
//    class B{
//         int na =9;
//         private final int[] x;
//
//
//        public B() {
//            this.x = null;
//            foo f = new B.foo();
//            int y = f.c;
//            List< ? extends Object> a = new ArrayList<A>();
//        }
//
//        static class foo{
//            private int c = 9;
//        }
//
//
//        @Override
//        public void foo1() {
//            System.out.println("bitch get out the way");
//        }
//
//        public void fooo(){
//            A.super.foo1();
//        }
//    }

import java.util.ArrayList;
import java.util.List;

    public class Mainn {
        public static void main(String[] args) {
            List<?> list = someMethodReturningWildcardList();

            // Getting an object from the list
            Object obj = list.get(0); // This will return an object of unknown type

            // You might need to cast it to a specific type if you know what type it is
            // For example:
            if (obj instanceof String) {
                String str = (String) obj;
                System.out.println("Got a string: " + str);
            } else {
                System.out.println("Got an object: " + obj);
            }
        }

        // Example method returning a list with a wildcard type
        public static List<?> someMethodReturningWildcardList() {
            // Assume this returns a list with a wildcard type
            List<Integer> l = new ArrayList<Integer>();
            l.add(3);
            return l;
        }
    }