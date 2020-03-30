class ReverseString {

    String reverse(String inputString) {
        if (inputString.length() == 1 || inputString.length() == 0) {
            return inputString;
        } else {
            return reverse(inputString.substring(1)) + inputString.substring(0, 1);
        }
    }
  
}