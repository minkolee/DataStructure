class Twofer {
    String twofer(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        //One for X, one for me.
        stringBuilder.append("One for ");
        if (name == null || name.length() == 0) {
            stringBuilder.append("you");
        } else {
            stringBuilder.append(name);
        }
        stringBuilder.append(", one for me.");
        return stringBuilder.toString();
    }
}
