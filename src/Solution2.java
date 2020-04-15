import java.util.HashMap;
import java.util.Map;

class Solution2 {

    private static Map<Character, Integer> map;


    static {
        map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

    }


    public int romanToInt(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1) {
                result = result + map.get(s.charAt(i));
            } else {
                if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) {
                    result = result + map.get(s.charAt(i));
                } else {
                    result = result - map.get(s.charAt(i));
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.romanToInt("III"));
        System.out.println(solution.romanToInt("IV"));
        System.out.println(solution.romanToInt("IX"));
        System.out.println(solution.romanToInt("LVIII"));
        System.out.println(solution.romanToInt("MCMXCIV"));

    }

}