package datastructure.chapter19;

import java.util.Arrays;
import java.util.Iterator;

public class TestDictionary {

    public static void main(String[] args) {
        LinearDictionary<String, String> dictionary = new LinearDictionary<>();

        dictionary.add("1st", "cony");
        dictionary.add("fdskj", "vjj");
        dictionary.add("cvjk", "vdd");

        System.out.println(dictionary.getValue("1st"));
        System.out.println(dictionary.getValue("1st2"));
        System.out.println(dictionary.getValue("cvjk"));

        System.out.println(dictionary.remove("cvjk"));
        System.out.println(dictionary.getValue("cvjk"));
        dictionary.add("cvjk", "vdd2231");
        System.out.println(dictionary.getValue("cvjk"));

        System.out.println(dictionary.contains("1st"));
        System.out.println(dictionary.contains("cony2"));
        System.out.println("------------------------------");

        dictionary.showAll();

        Iterator<String> iterator = dictionary.getKeyIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }

        Iterator<String> iterator2 = dictionary.getValueIterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());

        }

    }
}
