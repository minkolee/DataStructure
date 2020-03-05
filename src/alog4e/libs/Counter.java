package alog4e.libs;

public class Counter {

    private String name;

    private int count;

    public Counter(String id) {
        this.name = id;
    }

    public void increment() {
        count++;
    }

    int tally() {
        return this.count;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
