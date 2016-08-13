package ua.com.juja;

import java.util.Stack;

public class ExampleSolver {
    public static void main(String[] args) {
        String arr = "35+40*2/(10-5)";
        System.out.println(count(arr));
        arr = "35+40*2/(10-3)";
        System.out.println(count(arr));
        arr = "35+40*2/(10-5*7)";
        System.out.println(count(arr));
        arr = "6-(2+(1*3)*2)";
        System.out.println(count(arr));
        arr = "60-(2+1*3*2)";
        System.out.println(count(arr));
        arr = "5+0/(10-5)+2";
        System.out.println(count(arr));
    }

    public static String count(String task) {
        String[] parsedTask = parseToRPN(task);
        String res = stackMachine(parsedTask);
        return (res.substring(res.length()-2)).equals(".0") ? res.substring(0,res.length()-2) : res;
    }

    private static String[] parseToRPN(String task) {
        StringBuilder string = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < task.length(); i++) {
            char c = task.charAt(i);
            if (Character.isDigit(c)) {
                string.append(c);
            } else if (c == '(') {
                string.append(' ');
                stack.push(c);
            } else if (c == ')') {
                c = stack.pop();
                while (c != '(') {
                    string.append(' ');
                    string.append(c);
                    c = stack.pop();
                }
            } else {
                while (!stack.empty() && (getPriority(c) <= getPriority(stack.peek()))) {
                    string.append(' ');
                    string.append(stack.pop());
                }
                string.append(' ');
                stack.push(c);
            }
        }

        while (!stack.empty()) {
            string.append(' ');
            string.append(stack.pop());
        }
        System.out.println(string.toString());
        return string.toString().replace("  "," ").split(" ");
    }

    private static int getPriority(char c) {
        switch (c) {
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 3;
        }
    }


    private static String stackMachine(String[] arr) {
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            try {
                stack.push(Double.parseDouble(arr[i]));
            } catch (NumberFormatException e) {
                double b = stack.pop();
                double a = stack.pop();
                switch (arr[i]) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0) return "Division on zero";
                        stack.push(a / b);
                        break;
                }
            }
        }
        return String.valueOf(Math.ceil(stack.pop()*100)/100);
    }
}
