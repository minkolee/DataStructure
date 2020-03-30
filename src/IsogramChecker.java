class IsogramChecker {

    boolean isIsogram(String phrase) {

        if (phrase.length() == 0) {
            return true;
        }

        char[] charArray = new char[26];

        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) <= 122 && phrase.charAt(i) >= 97) {
                if (charArray[phrase.charAt(i) - 97] == 1) {
                    return false;
                } else {
                    charArray[phrase.charAt(i) - 97]++;
                }
            } else if (phrase.charAt(i) <= 90 && phrase.charAt(i) >= 65) {
                if (charArray[phrase.charAt(i) - 65] == 1) {
                    return false;
                } else {
                    charArray[phrase.charAt(i) - 65]++;
                }
            }
        }

        return true;
    }

}
