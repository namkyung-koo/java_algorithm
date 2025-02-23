package BOJ10000_10999; // 10814 - 나이순 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ10814 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<Integer, List<String>> map = new TreeMap<>();

        int age = 0;
        String userName = "";

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            age = Integer.parseInt(st.nextToken());
            userName = st.nextToken();
            map.putIfAbsent(age, new LinkedList<>());
            map.get(age).add(userName);
        }

        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            int key = entry.getKey();
            for (String value : entry.getValue()) {
                System.out.println(key + " " + value);
            }
        }
    }
}
