package datastructure.ex03;

import java.util.List;

public class Test {


    public static void main(String[] args) {
        CarA carA1 = new CarA(10, "dfkj");
        CarA carA2 = new CarA(17, "fd");
        CarA carA3 = new CarA(3, "asd");

        System.out.println(carA1.compareTo(carA2));

        CarB carB1 = new CarB(29, "dfkbbvcj");
        CarB carB2 = new CarB(21, "bjkd");

        System.out.println(carB2.compareTo(carB1));
        System.out.println(carA2.compareTo(carB1));
        System.out.println(carB2.compareTo(carA3));

        CarA[] carAS = new CarA[]{carA1, carA2, carA3};
        CarB[] cars = new CarB[]{carB1, carB2};
        CarA mincara = findMin(carAS);
        CarB mincarb = findMin(cars);

        System.out.println(carAS.getClass() == cars.getClass());
        System.out.println(carAS.getClass());
        System.out.println(cars.getClass());

    }

    public static <T extends Comparable<? super T>> T findMin(T[] array) {

        T min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

}
