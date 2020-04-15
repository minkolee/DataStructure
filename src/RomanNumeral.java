import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {

    private int nubmer;

    private static Map<Integer, Map<Integer, String>> map;

    static {
        map = new HashMap<>();

        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "I");
        map1.put(2, "II");
        map1.put(3, "III");
        map1.put(4, "IV");
        map1.put(5, "V");
        map1.put(6, "VI");
        map1.put(7, "VII");
        map1.put(8, "VIII");
        map1.put(9, "IX");

        map.put(1, map1);

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "X");
        map2.put(2, "XX");
        map2.put(3, "XXX");
        map2.put(4, "XL");
        map2.put(5, "L");
        map2.put(6, "LX");
        map2.put(7, "LXX");
        map2.put(8, "LXXX");
        map2.put(9, "XC");

        map.put(2, map2);

        Map<Integer, String> map3 = new HashMap<>();
        map3.put(1, "C");
        map3.put(2, "CC");
        map3.put(3, "CCC");
        map3.put(4, "CD");
        map3.put(5, "D");
        map3.put(6, "DC");
        map3.put(7, "DCC");
        map3.put(8, "DCCC");
        map3.put(9, "CM");

        map.put(3, map3);

        Map<Integer, String> map4 = new HashMap<>();
        map4.put(1, "M");
        map4.put(2, "MM");
        map4.put(3, "MMM");

        map.put(4, map4);

    }



    private String parseRoman(int nubmer, int digit) {
        if (nubmer == 0) {
            return "";
        }

        return parseRoman(nubmer / 10, digit + 1) + getSingleDigital(digit, nubmer % 10);
    }


    private String getSingleDigital(int digits, int number) {

        if (number == 0) {
            return "";
        } else {
            return map.get(digits).get(number);
        }
    }



    public String intToRoman(int num) {
        this.nubmer = num;
        return parseRoman(nubmer, 1);
    }

}