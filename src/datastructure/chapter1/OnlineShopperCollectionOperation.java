package datastructure.chapter1;


import java.util.Arrays;

public class OnlineShopperCollectionOperation {

    public static void main(String[] args) {

        ResizableArrayBagComparable<Item> shoppingCart = new ResizableArrayBagComparable<>();
        for (int i = 0; i < 20; i++) {
            shoppingCart.add(new Item("item", i));
        }
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
        //0 1 2 3 4 5 6 7 8 9

        //0 1 2 3 4
        //7 8 9

        //5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29
        //0 1 2 3 4 5 6

        //20 21 22 23 24 25 26 27 28 29


        //

        for (int i = 0; i < 10; i++) {
            shoppingCart.add(new Item("item", i));
        }

        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrnetSize());

        ResizableArrayBagComparable<Item> shoppingCart2 = new ResizableArrayBagComparable<>();

        for (int i = 5; i < 30; i++) {
            shoppingCart2.add(new Item("item", i));
        }

        for (int i = 0; i < 7; i++) {
            shoppingCart2.add(new Item("item", i));
        }

//        for (int i = 0; i < 20; i++) {
//            shoppingCart2.add(new Item("item", i * 2));
//        }

        System.out.println(Arrays.toString(shoppingCart2.toArray()));
        System.out.println(shoppingCart2.getCurrnetSize());

        ResizableArrayBag<Item> difference = shoppingCart.difference(shoppingCart2);

        System.out.println(Arrays.toString(difference.toArray()));
        System.out.println(difference.getCurrnetSize());
        System.out.println(difference.getInternalSize());

        ResizableArrayBag<Item> difference2 = shoppingCart2.difference(shoppingCart);

        System.out.println(Arrays.toString(difference2.toArray()));
        System.out.println(difference2.getCurrnetSize());
        System.out.println(difference2.getInternalSize());

    }

    public static <T extends Comparable<T>> ResizableArrayBag<T> transferFixedBagToResizableBag(ArrayBag<T> bag) {
        ResizableArrayBag<T> resizableArrayBag = new ResizableArrayBag<>(bag.getCurrnetSize());
        for (int i = 0; i < bag.getCurrnetSize(); i++) {
            resizableArrayBag.add((bag.toArray())[i]);
        }
        return resizableArrayBag;
    }

}
