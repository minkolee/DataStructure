package datastructure.chapter2;


import datastructure.chapter1.ArrayBag;
import datastructure.chapter1.Item;
import datastructure.chapter1.ResizableArrayBag;
import datastructure.chapter1.ResizableArrayBagComparable;

import java.util.Arrays;

public class OnlineShopperLinkedList {

    public static void main(String[] args) {

        Item[] items = {
                new Item("Bird feeder", 2050),
                new Item("Squirrel guard", 1547),
                new Item("Bird bath", 4499),
                new Item("Sunflower Seeds", 1295),
        };

        LinkedBag<Item> shoppingCart = new LinkedBag<>();

        //添加了四个项目
        for (Item nextItem : items) {
            shoppingCart.add(nextItem);
        }
        shoppingCart.add(items[2]);

        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println(shoppingCart.getFrequencyOf(items[2]));

        System.out.println(shoppingCart.contains(items[3]));
        System.out.println(shoppingCart.contains(new Item("fdsjk", 321)));

        System.out.println("测试删除");
        shoppingCart.remove();
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        shoppingCart.remove(items[3]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrentSize());

        shoppingCart.remove(items[2]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrentSize());


        shoppingCart.remove(items[1]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrentSize());


        shoppingCart.remove(items[0]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrentSize());

        shoppingCart.add(items[2]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrentSize());

        shoppingCart.remove(items[2]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrentSize());
    }

    public static <T extends Comparable<T>> ResizableArrayBag<T> transferFixedBagToResizableBag(ArrayBag<T> bag) {
        ResizableArrayBag<T> resizableArrayBag = new ResizableArrayBag<>(bag.getCurrnetSize());
        for (int i = 0; i < bag.getCurrnetSize(); i++) {
            resizableArrayBag.add((bag.toArray())[i]);
        }

        return resizableArrayBag;
    }


}
