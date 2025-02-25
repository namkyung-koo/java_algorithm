package BOJ5000_5999; // 5430 - AC

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ5430 {

    static boolean isR, isError, error;
    static int T, n;
    static String p, input;
    static Deque<Integer> deque;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            // 명령어 저장
            st = new StringTokenizer(br.readLine());
            p = st.nextToken();

            // 배열의 크기 저장
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            // 배열 저장("[", "]" 제외)
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();
            if (n == 0 && p.contains("D")) {
                System.out.println("error");
                continue;
            }
            String token = st.nextToken();
            if (token.length() > 2) {
                input = token.substring(1, token.length() - 1);
                st = new StringTokenizer(input, ",");
            } else
                st = new StringTokenizer("");

            // 배열 정수형 원소들만 큐에 저장
            deque = new ArrayDeque<>();
            while (st.hasMoreTokens()) {
                deque.addLast(Integer.parseInt(st.nextToken()));
            }
            isR = false;
            isError = false;

            // 함수 2개로 분기
            for (int j = 0; j < p.length(); j++) {

                if (p.charAt(j) == 'R') {
                    if (!isR)
                        isR = true;
                    else
                        isR = false;
                } else if (p.charAt(j) == 'D') {
                    if (deque.isEmpty()) {
                        System.out.println("error");
                        isError = true;
                        break;
                    }
                    if (!isR)
                        deque.pollFirst();
                    else
                        deque.pollLast();
                }
            }
            if (!isError) {
                sb.append("[");
                while (!deque.isEmpty()) {
                    if (!isR)
                        sb.append(deque.pollFirst());
                    else
                        sb.append(deque.pollLast());
                    if (!deque.isEmpty()) {
                        sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }
        }
    }
}
