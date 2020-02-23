package datastructure.chapter1;


import java.util.Arrays;

public class OnlineShopperReduce {

    public static void main(String[] args) {

        ResizableArrayBag<Item> shoppingCart = new ResizableArrayBag<>(4);

        for (int i = 0; i < 17; i++) {
            shoppingCart.add(new Item("item", i * 10));
        }

        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println(shoppingCart.getCurrnetSize());

        for (int j = 0; j < 8; j++) {
            shoppingCart.remove();
            System.out.println("删除第" + j + "次之后数组的长度是: " + shoppingCart.getInternalSize());
            System.out.println("删除第" + j + "次之后其中的元素数量是: " + shoppingCart.getCurrnetSize());
        }


    }
}
