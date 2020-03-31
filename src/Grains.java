import java.math.BigInteger;

class Grains {

    private BigInteger[] grains = new BigInteger[64];
    private static BigInteger fixedRate = new BigInteger("2");

    public Grains() {
        grains[0] = new BigInteger("1");

        for (int i = 1; i < 64; i++) {
            grains[i] = grains[i - 1].multiply(fixedRate);
        }
    }

    BigInteger grainsOnSquare(final int square) {

        if (square < 1 || square > 64) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }

        return grains[square - 1];
    }

    BigInteger grainsOnBoard() {
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < grains.length; i++) {
            sum = sum.add(grains[i]);
        }
        return sum;
    }

}
