package datastructure.chapter08;

import datastructure.ex03.CarA;
import datastructure.ex03.CarB;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        CarA carA1 = new CarA(20, "dfkj");
        CarA carA2 = new CarA(17, "fd");
        CarA carA3 = new CarA(34, "asd");
        CarA carA4 = new CarA(15, "asd");

        System.out.println(carA1.compareTo(carA2));

        CarB carB1 = new CarB(29, "dfkbbvcj");
        CarB carB2 = new CarB(21, "bjkd");
        CarB carB3 = new CarB(33, "bjsakd");
        CarB carB4 = new CarB(22, "asd");

        System.out.println(carB2.compareTo(carB1));
        System.out.println(carA2.compareTo(carB1));
        System.out.println(carB2.compareTo(carA3));

        CarA[] carAS = new CarA[]{carA1, carA2, carA3, carA4};
        CarB[] cars = new CarB[]{carB1, carB2,carB3,carB4};

        System.out.println(Arrays.toString(carAS));

        SelectionSort.resursionSort(cars);
        System.out.println(Arrays.toString(cars));

    }
}
