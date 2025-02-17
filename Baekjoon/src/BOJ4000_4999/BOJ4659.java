package BOJ4000_4999;// 4659 비밀번호 발음하기

import java.util.Scanner;

public class BOJ4659 {

    // 1. 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
    // 2. 모임 혹은 자음이 3개 연속으로 오면 안된다.
    // 3. ee와 oo를 제외하고 같은 글자가 연속으로 두 번 오면 안된다.
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int consonant = 0, vowel = 0;
        boolean first = false, second = true, third = true;

        String input = "";
        int len;
        char c;

        while (true) {
            input = sc.nextLine();
            if (input.equals("end"))
                break;

            len = input.length();

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    first = true;
                    vowel++;
                    consonant = 0;
                } else {
                    consonant++;
                    vowel = 0;
                }

                if (vowel >= 3 || consonant >= 3) {
                    second = false;
                    break;
                }

                if (i < len - 1) {
                    if (c != 'e' && c != 'o' && c == input.charAt(i + 1)) {
                        third = false;
                        break;
                    }
                }
            }
            if (first && second && third) {
                System.out.println("<" + input + "> is acceptable.");
            } else {
                System.out.println("<" + input + "> is not acceptable.");
            }
            vowel = 0;
            consonant = 0;
            first = false;
            second = true;
            third = true;
        }
    }
}
