class RaindropConverter {

    String convert(int number) {

        StringBuilder stringBuilder = new StringBuilder();

        boolean found = false;

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                if (i == 3) {
                    stringBuilder.append("Pling");
                    found = true;
                } else if (i == 5) {
                    stringBuilder.append("Plang");
                    found = true;
                } else if (i == 7) {
                    stringBuilder.append("Plong");
                    found = true;
                }
            }
        }
        if (found) {
            return stringBuilder.toString();
        } else {
            return String.valueOf(number);
        }
    }

}
