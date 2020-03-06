package datastructure.chapter07.project.p05;

class KnapsackItem {
    private int size;
    private int value;
    private String name;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KnapsackItem(int size, int value, String name) {
        this.size = size;
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Knapsack{" +
                "size=" + size +
                ", value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}