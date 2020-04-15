import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        if (s.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack.push(c);
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.pop() != '{') {
                        return false;
                    }
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.pop() != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.pop() != '[') {
                        return false;
                    }
                    break;
            }


        }
        return stack.isEmpty();
    }
}