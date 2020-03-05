package datastructure.chapter07.project;

public class P01 {

    //这个应该就是求平方数的高斯逼近方法.
    public static double squareRoot(double number, double lowGuess, double highGuess, double tolerance) {
        double newGuess = (lowGuess + highGuess) / 2;

        if ((highGuess - newGuess) / 2 < tolerance) {
            return newGuess;
        } else if (newGuess * newGuess > number) {
            return squareRoot(number, lowGuess, newGuess, tolerance);
        } else if (newGuess * newGuess < number) {
            return squareRoot(number, newGuess, highGuess, tolerance);
        } else {
            return newGuess;
        }
    }


    public static void main(String[] args) {
        System.out.println(squareRoot(250, 0, 125, 0.00005));
        System.out.println(Math.sqrt(250));

    }
}
