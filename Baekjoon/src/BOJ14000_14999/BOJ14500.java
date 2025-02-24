package BOJ14000_14999; // 14500 - 테트로미노

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {

    static int n, m;
    static int max = 0;
    static int[][][] tetrominoes = {
            // O 블록 (1개)
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            // I 블록 (2개)
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            // L 블록 (4개)
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 0}, {1, 1}, {1, 2}},
            // J 블록 (L 블록의 대칭, 4개)
            {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            // T 블록 (4개)
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 1}, {1, 0}, {1, 1}, {2, 1}},
            {{0, 1}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 0}},
            // Z 블록 (2개)
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            {{0, 1}, {1, 0}, {1, 1}, {2, 0}},
            // S 블록 (2개)
            {{0, 1}, {0, 2}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] paper = new int[n][m];

        // 데이터 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int[][] shape : tetrominoes) {
                    int sum = 0;
                    boolean isValid = true;

                    for (int[] block : shape) {
                        int x = i + block[0], y = j + block[1];
                        if (x < 0 || x >= n || y < 0 || y >= m) {
                            isValid = false;
                            break;
                        }
                        sum += paper[x][y];
                    }
                    if (isValid) max = Math.max(max, sum);
                }
            }
        }
        System.out.println(max);
    }
}
