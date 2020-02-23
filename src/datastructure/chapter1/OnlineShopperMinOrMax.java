package datastructure.chapter1;


import java.util.Arrays;
import java.util.Comparator;

public class OnlineShopperMinOrMax {

    public static void main(String[] args) {

        Item[] items = {
                new Item("Bird feeder", 50),
                new Item("Squirrel guard", 1547),
                new Item("Bird bath", 4499),
                new Item("Sunflower Seeds", 1295),
                new Item("Sunflower", 9999),
                new Item("Sunflower", 293),
        };

        ResizableArrayBagComparable<Item> shoppingCart = new ResizableArrayBagComparable<>(4);


        for (Item nextItem : items) {
            shoppingCart.add(nextItem);
        }
        System.out.println(Arrays.toString(shoppingCart.toArray()));

//        Item max = shoppingCart.getMax(Comparator.comparingInt(Item::getPrice));
//        System.out.println("最大的对象是: " + max);
//        shoppingCart.removeMax(Comparator.comparingInt(Item::getPrice));
//        System.out.println(Arrays.toString(shoppingCart.toArray()));
//
//        Item min = shoppingCart.getMin(Comparator.comparingInt(Item::getPrice));
//        System.out.println("最小的对象是: " + min);
//        shoppingCart.removeMin(Comparator.comparingInt(Item::getPrice));
//        System.out.println(Arrays.toString(shoppingCart.toArray()));
//        System.out.println("小于4499价格组成的包");
//        System.out.println(Arrays.toString(shoppingCart.getAllLessThan(items[2], Comparator.comparingInt(Item::getPrice)).toArray()));
//

        ResizableArrayBagComparable<Item> shoppingCart2 = new ResizableArrayBagComparable<>();
        for (Item nextItem : items) {
            shoppingCart2.add(nextItem);
        }

        shoppingCart.add(items[2]);
        shoppingCart.remove(items[3]);
        shoppingCart2.add(items[2]);
        shoppingCart2.remove(items[3]);
        System.out.println(shoppingCart.equals(shoppingCart2));

        ResizableArrayBagComparable<Item> shoppingCart3 = new ResizableArrayBagComparable<>();
        shoppingCart3.add(new Item("fd", 66));

        System.out.println("2和3是否相同: " + shoppingCart.equals(shoppingCart3));

    }

    public static <T extends Comparable<T>> ResizableArrayBag<T> transferFixedBagToResizableBag(ArrayBag<T> bag) {
        ResizableArrayBag<T> resizableArrayBag = new ResizableArrayBag<>(bag.getCurrnetSize());
        for (int i = 0; i < bag.getCurrnetSize(); i++) {
            resizableArrayBag.add((bag.toArray())[i]);
        }


        return resizableArrayBag;
    }

}
