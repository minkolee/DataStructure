package thinkinginjava.chapter02;

import java.util.*;

/**
 * The first program
 * @author Minko
 * @author http://conyli.cc
 * @version 0.0.1
 */
public class HelloDate {

    /**
     * Basic program shows all properties
     * @param args array of string arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello, it's ");
        System.out.println(new Date());

        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));

    }

}
