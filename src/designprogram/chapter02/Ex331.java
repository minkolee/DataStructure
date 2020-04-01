package designprogram.chapter02;

public class Ex331 {

    private static double inchToCmRate = 2.54;

    private static double feetToInchRate = 12;

    private static double yardsToFeetRate = 3;

    private static double rodsToYardsRate = 5.5;

    private static double furlongsToRodsRate = 40;

    private static double milesToFurlongsRate = 8;

    public static double inchToCentimeter(double n) {
        return n * inchToCmRate;
    }

    public static double feetToInches(double n){
        return n * feetToInchRate;
    }

    public static double yardsToFeet(double n) {
        return n * yardsToFeetRate;
    }

    public static double rodsToYards(double n) {
        return n * rodsToYardsRate;
    }

    public static double furlongsToRods(double n) {
        return n * furlongsToRodsRate;
    }

    public static double milesToFurlongs(double n) {
        return n * milesToFurlongsRate;
    }

    public static double feetToCentimeter(double n) {
        return inchToCentimeter(feetToInches(n));
    }

    public static double yardsToCentimeter(double n) {
        return feetToCentimeter(yardsToFeet(n));
    }

    public static double rodsToInches(double n) {
        return feetToInches(yardsToFeet(rodsToYards(n)));
    }

    public static double milesToFeet(double n) {
        return yardsToFeet(rodsToYards(furlongsToRods(milesToFurlongs(n))));

    }

}
