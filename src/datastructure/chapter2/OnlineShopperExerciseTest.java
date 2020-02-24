package datastructure.chapter2;


import alog4e.chapter01.section02.bag.Bag;
import datastructure.chapter1.ArrayBag;
import datastructure.chapter1.Item;
import datastructure.chapter1.ResizableArrayBag;

import java.util.Arrays;
import java.util.Comparator;

public class OnlineShopperExerciseTest {

    public static void main(String[] args) {
//
//        Item[] items = {
//                new Item("Bird feeder", 2050),
//                new Item("Squirrel guard", 1547),
//                new Item("Bird bath", 4499),
//                new Item("Sunflower Seeds", 1295),
//                new Item("Grim Dawn", 3820),
//        };
//
        LinkedBag<String> shoppingCart = new LinkedBag<>();
        LinkedBag<String> shoppingCart2 = new LinkedBag<>();


        shoppingCart.add("10");
        shoppingCart.add("20");
        shoppingCart.add("20");
        shoppingCart.add("30");
        shoppingCart.add("50");

        shoppingCart2.add("10");
        shoppingCart2.add("10");
        shoppingCart2.add("10");
        shoppingCart2.add("20");
        shoppingCart2.add("20");
        shoppingCart2.add("30");
        shoppingCart2.add("30");
        shoppingCart2.add("30");
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(Arrays.toString(shoppingCart2.toArray()));

        System.out.println(Arrays.toString(shoppingCart2.difference(shoppingCart).toArray()));



//
//        for (Item i : items) {
//            shoppingCart.add(i);
//        }
//        shoppingCart.add(items[2]);
//
//
//        for (Item i : items) {
//            shoppingCart2.add(i);
//        }
//
//        System.out.println(shoppingCart.equals(shoppingCart2));

        //用数组构造器






    }

    public static <T extends Comparable<T>> ResizableArrayBag<T> transferFixedBagToResizableBag(ArrayBag<T> bag) {
        ResizableArrayBag<T> resizableArrayBag = new ResizableArrayBag<>(bag.getCurrnetSize());
        for (int i = 0; i < bag.getCurrnetSize(); i++) {
            resizableArrayBag.add((bag.toArray())[i]);
        }

        return resizableArrayBag;
    }


}
