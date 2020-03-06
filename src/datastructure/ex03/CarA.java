package datastructure.ex03;

public class CarA implements Comparable<CarA> {

    private int weight;

    private String name;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarA(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarA{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(CarA carA) {
        return this.weight - carA.weight;

    }
}
