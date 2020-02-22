package datastructure.chapter1;


import java.util.Arrays;

public class OnlineShopperDeleteAll {

    public static void main(String[] args) {

        Item[] items = {
                new Item("Bird feeder", 2050),
                new Item("Squirrel guard", 1547),
                new Item("Bird bath", 4499),
                new Item("Sunflower Seeds", 1295),
        };

        ResizableArrayBag<Item> shoppingCart = new ResizableArrayBag<>(10);

        System.out.println("添加之前的数组是: " + Arrays.toString(shoppingCart.toArray()));

        //添加了四个项目


        //又添加4次items[3]
        for (int i = 0; i <145; i++) {
            shoppingCart.add(items[2]);
        }


        System.out.println("添加了8个项目");
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println("现在的长度是: " + shoppingCart.getInternalSize());

        System.out.println("拥有几个items[1]: " + shoppingCart.getFrequencyOf(items[2]));

        System.out.println("删除的items[1]: " + shoppingCart.removeEvery(items[2]));
        System.out.println("删除所有items[1]");
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println("现在的长度是: " + shoppingCart.getInternalSize());

    }
}
