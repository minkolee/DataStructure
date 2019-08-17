package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exer1121 {

    public static void score() {
        Scanner scan = new Scanner(System.in);
        String input;
        while (true) {
            try {
                input = scan.nextLine();
            } catch (NoSuchElementException ex) {
                StdOut.printf("End of file.");
                break;
            }
            String name = input.split(" ")[0];
            int score1 = Integer.parseInt(input.split(" ")[1]);
            int score2 = Integer.parseInt(input.split(" ")[2]);
            StdOut.printf("%-10s\t%10d\t%10d\t%10.3f\n", name, score1, score2, ((double) score1) / score2);
            }
        }

    public static void main(String[] args) {
        score();
    }
}
