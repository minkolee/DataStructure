import java.util.ArrayList;
import java.util.List;

public class Anagram {

    private String standard;

    public Anagram(String s) {
        this.standard = s.toLowerCase();
    }


    public List<String> match(List<String> list) {

        List<String> result = new ArrayList<>();

        for (String s : list) {
            if (isRearrange(s)) {
                result.add(s);
            }
        }

        return result;

    }

    private boolean isRearrange(String outer) {
        String outerLowerCase = outer.toLowerCase();

        if (standard.equals(outerLowerCase)) {
            return false;
        }

        int[] array = new int[26];

        for (int i = 0; i < standard.length(); i++) {
            if (isValid(standard.charAt(i))) {
                array[standard.charAt(i) - 97] = array[standard.charAt(i) - 97] + 1;
            }
        }

        for (int i = 0; i < outerLowerCase.length(); i++) {
            if (isValid(outerLowerCase.charAt(i))) {
                array[outerLowerCase.charAt(i) - 97] = array[outerLowerCase.charAt(i) - 97] - 1;
            }
        }


//        for (int i = 0; i < outerLowerCase.length(); i++) {
//            System.out.println(i);
//            System.out.println(outerLowerCase.charAt(i));
//            array[outerLowerCase.charAt(i) - 97] = array[standard.charAt(i) - 97] - 1;
//        }

//        System.out.println(Arrays.toString(array));

        for (int i : array) {
            if (i != 0) {
                return false;
            }
        }

        return true;

    }

    private boolean isValid(char c) {
        return c >= 97 && c <= 122;
    }

}