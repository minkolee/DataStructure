package alog4e.chapter01.section02;

import alog4e.libs.StdOut;

import java.util.Objects;
import java.util.Random;

public class Rational {
    private long numerator;
    private long denominator;

    Rational(int numerator, int denominator) {
        this.numerator = numerator / gcd(numerator, denominator);
        this.denominator = denominator / gcd(numerator, denominator);
        //如果有负数, 统一到分子上变为负数, 分母为正数
        if (this.denominator < 0) {
            this.numerator = -(this.numerator);
            this.denominator = Math.abs(this.denominator);
        }
    }

    private static long gcd(long p, long q) {
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

    public Rational plus(Rational b) {
        return new Rational((int) (this.numerator * b.denominator + this.denominator * b.numerator), (int) (this.denominator * b.denominator));
    }

    public Rational minus(Rational b) {
        return new Rational((int) (this.numerator * b.denominator - this.denominator * b.numerator), (int) (this.denominator * b.denominator));
    }

    public Rational times(Rational b) {
        return new Rational((int) (this.numerator * b.numerator), (int) (this.denominator * b.denominator));
    }

    public Rational divides(Rational b) {
        return new Rational((int) (this.numerator * b.denominator), (int) (this.denominator * b.numerator));
    }

    public boolean equals(Rational that) {
        if (this == that) return true;
        if (that == null || this.getClass() != that.getClass()) return false;
        return this.numerator == that.numerator &&
                this.denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }


    @Override
    public String toString() {
            return "Rational{" +
                    numerator +
                    "/" + denominator +
                    '}';
    }

    public static void main(String[] args) {
        Rational r1 = new Rational(2, -20);
        Rational r2 = new Rational(-1, 10);
        Rational result = r2.minus(r1);
        StdOut.println(result);
        result = r1.plus(r2);
        StdOut.println(result);
        result = r1.times(r2);
        StdOut.println(result);
        result = r1.divides(r2);
        StdOut.println(result);
        StdOut.print(r1.equals(r2));
        StdOut.println(r1);
        StdOut.println(r2);
    }
}
