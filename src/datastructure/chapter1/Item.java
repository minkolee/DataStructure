package datastructure.chapter1;

public class Item implements Comparable<Item> {

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

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Item.class) {
            return ((Item) obj).getName().equals(this.name) && ((Item) obj).getPrice() == this.price;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Item o) {
        return this.price - o.price;
    }
}
