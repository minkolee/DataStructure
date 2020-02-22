package datastructure.chapter1;


import java.util.Arrays;

public class OnlineShopperReplace {

    public static void main(String[] args) {

        Item[] items = {
                new Item("Bird feeder", 2050),
                new Item("Squirrel guard", 1547),
                new Item("Bird bath", 4499),
                new Item("Sunflower Seeds", 1295),
        };

        ResizableArrayBag<Item> shoppingCart = new ResizableArrayBag<>(2);

        System.out.println("添加之前的数组是: " + Arrays.toString(shoppingCart.toArray()));

        //添加了四个项目
        for (Item nextItem : items) {
            shoppingCart.add(nextItem);
        }

        System.out.println("添加了四个项目");
        //测试所有方法
        System.out.println(shoppingCart.getCurrnetSize());
        System.out.println("添加之后的长度是: "+shoppingCart.getCurrnetSize());
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("替换items[0] 为 items[1]");

        System.out.println(shoppingCart.replace(new Item("cony", 6), items[0]));

        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("开始随机删除");
        System.out.println(shoppingCart.removeRandom());
        System.out.println("当前的包中的数量是:" + shoppingCart.getCurrnetSize());

        System.out.println(shoppingCart.removeRandom());
        System.out.println("当前的包中的数量是:" + shoppingCart.getCurrnetSize());

        System.out.println(shoppingCart.removeRandom());
        System.out.println("当前的包中的数量是:" + shoppingCart.getCurrnetSize());

        System.out.println(shoppingCart.removeRandom());
        System.out.println("当前的包中的数量是:" + shoppingCart.getCurrnetSize());

        System.out.println(shoppingCart.removeRandom());
        System.out.println("当前的包中的数量是:" + shoppingCart.getCurrnetSize());

    }
}
