package datastructure.chapter5;


public class Test {

    public static void main(String[] args) {

//        System.out.println(Postfix.convertToPostfix("6/3*(1+(9-7))"));
//        System.out.println(Postfix.convertToPostfix("(a+b)/(c-d)"));
//        System.out.println(Postfix.convertToPostfix("a/(b-c)*d"));
//        System.out.println(Postfix.convertToPostfix("a-(b/(c-d)*e+f)^g"));
//        System.out.println(Postfix.convertToPostfix("(a-b*c)/(d*e^f*g+h)"));
//
//        System.out.println(Postfix.evaluatePostfix("24+3/"));
//        System.out.println(Postfix.evaluatePostfix("26+35-/"));
//
//        System.out.println(Postfix.evaluatePostfix(Postfix.convertToPostfix("6/3*(1+(9-7))")));
//

//        System.out.println(Postfix.evaluateInfix("(3+2)*9/4"));


        int a = 2, b = 3, c = 4, d = 5, e = 6;

        String expression1 = "2+3*4-9";
        String expression2 = "(2+6)/(3-5)";
        String expression3 = "2+(3+4*5)-6/2";
        String expression4 = "6-3*4^2+5";

        System.out.println(Postfix.evaluateInfix(expression1));
        System.out.println(Postfix.evaluateInfix(expression2));
        System.out.println(Postfix.evaluateInfix(expression3));
        System.out.println(Postfix.evaluateInfix(expression4));

    }

    



}
