package datastructure.chapter1;

public class OnlineShopper {


    private static class Item {
        private int price;
        private String name;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Item() {

        }

        public Item(String name, int price) {

            this.price = price;
            this.name = name;
        }
    }


    public static void main(String[] args) {

        Item[] items = {
                new Item("Bird feeder", 2050),
                new Item("Squirrel guard", 1547),
                new Item("Bird bath", 4499),
                new Item("Sunflower Seeds", 1295),
        };

        BagInterface<Item> shoppingCart = new Bag<>();

        int totalCost = 0;

        for (Item nextItem : items) {
            shoppingCart.add(nextItem);
            totalCost += nextItem.getPrice();
        }

        while (!shoppingCart.isEmpty()) {
            System.out.println(shoppingCart.remove());
        }
        System.out.println("Total cost: " + "\t$" + totalCost / 100 + "." + totalCost % 100);
    }
}
