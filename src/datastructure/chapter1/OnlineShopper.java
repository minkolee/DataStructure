package datastructure.chapter1;


import java.util.Arrays;

public class OnlineShopper {

    public static void main(String[] args) {

        Item[] items = {
                new Item("Bird feeder", 2050),
                new Item("Squirrel guard", 1547),
                new Item("Bird bath", 4499),
                new Item("Sunflower Seeds", 1295),
        };

        ArrayBag<Item> shoppingCart = new ArrayBag<>(4);

        //添加了四个项目
        for (Item nextItem : items) {
            shoppingCart.add(nextItem);
        }

        System.out.println(Arrays.toString(shoppingCart.toArray()));


        ResizableArrayBag<Item> bag = transferFixedBagToResizableBag(shoppingCart);

        System.out.println(Arrays.toString(bag.toArray()));


    }

    public static <T extends Comparable<T>> ResizableArrayBag<T> transferFixedBagToResizableBag(ArrayBag<T> bag) {
        ResizableArrayBag<T> resizableArrayBag = new ResizableArrayBag<>(bag.getCurrnetSize());
        for (int i = 0; i < bag.getCurrnetSize(); i++) {
            resizableArrayBag.add((bag.toArray())[i]);
        }


        return resizableArrayBag;
    }

}
