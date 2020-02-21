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

        ArrayBag<Item> shoppingCart = new ArrayBag<>();

        //添加了四个项目
        for (Item nextItem : items) {
            shoppingCart.add(nextItem);
        }

        System.out.println("添加了四个项目");
        //测试所有方法
        System.out.println(shoppingCart.getCurrnetSize());

        System.out.println("是否为空");
        System.out.println(shoppingCart.isEmpty());
        System.out.println("现在的包内容");
        System.out.println(Arrays.toString(shoppingCart.toArray()));
        System.out.println("添加一个相同的项目");
        shoppingCart.add(items[3]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("第0项出现的次数");
        System.out.println(shoppingCart.getFrequencyOf(items[0]));
        System.out.println("第3项出现的次数");
        System.out.println(shoppingCart.getFrequencyOf(items[3]));
        System.out.println("不存在的项目出现的次数");
        System.out.println(shoppingCart.getFrequencyOf(new Item("fsddjk", 1209)));

        System.out.println("是否包含items[3]");
        System.out.println(shoppingCart.contains(items[3]));

        System.out.println("是否包含一个与items[1]相等的新建对象:");
        System.out.println(shoppingCart.contains(new Item("Squirrel guard", 1547)));

        System.out.println("现在的包内容");
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("删除与items[1]相等的新建对象");
        shoppingCart.remove(new Item("Squirrel guard", 1547));
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("删除items[3]");
        shoppingCart.remove(items[3]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("删除items[3]");
        shoppingCart.remove(items[3]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("删除items[0]");
        shoppingCart.remove(items[0]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("再添加items[3]");
        shoppingCart.add(items[3]);
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println("删除干净");
        System.out.println(shoppingCart.remove());
        System.out.println(shoppingCart.isEmpty());
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println(shoppingCart.remove());
        System.out.println(shoppingCart.isEmpty());
        System.out.println(Arrays.toString(shoppingCart.toArray()));

        System.out.println(shoppingCart.remove());
        System.out.println(shoppingCart.isEmpty());
        System.out.println(Arrays.toString(shoppingCart.toArray()));

    }
}
