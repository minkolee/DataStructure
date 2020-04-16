class Solution {

    public int strStr(String haystack, String needle) {

        int i;

        boolean found = true;


        for (i = 0; i < haystack.length(); i++) {

            int j = 0;

            while (found) {
                if (j + i > haystack.length()) {
                    return -1;
                }

                if (needle.charAt(j) != haystack.charAt(i)) {
                    found = false;
                    i = i + j + 1;
                }
                j++;
            }

            if (found) {
                return i;
            }

        }

        if (found) {
            return i;
        } else {
            return -1;
        }



    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.strStr("hello", "ll"));
    }


}