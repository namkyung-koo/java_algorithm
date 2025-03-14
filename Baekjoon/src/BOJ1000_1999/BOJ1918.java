package BOJ1000_1999; // 1918 - 후위 표기식

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ1918 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> op = new HashMap<>();
        op.put('+', 0);
        op.put('-', 0);
        op.put('*', 1);
        op.put('/', 1);
        op.put('(', -1);

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) // c가 피연산자인 경우 (예시. A, B, C, ...)
                sb.append(c);
            else if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peekLast() != '(') {
                    sb.append(stack.pollLast());
                }
                stack.pollLast(); // '(' 버리기
            } else { // c가 연산자인 경우 (예시. '+', '-', '*', '/')
                while (!stack.isEmpty() && op.get(stack.peekLast()) >= op.get(c)) {
                    sb.append(stack.pollLast());
                }
                stack.add(c);
            }
        }
        while (!stack.isEmpty())
            sb.append(stack.pollLast());
        System.out.println(sb);
    }
}
