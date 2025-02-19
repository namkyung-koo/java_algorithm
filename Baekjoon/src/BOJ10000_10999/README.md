# BOJ 10000번 ~ 10999번 문제 풀이

## 📌 BOJ 10845 - 큐
- **문제 링크**: [백준 10845번](https://www.acmicpc.net/problem/10845)
- **풀이 요약**: ArrayDeque을 사용하여 해결

## 📌 BOJ 10828 - 스택
- **문제 링크**: [백준 10828번](https://www.acmicpc.net/problem/10828)
- **풀이 요약**: 처음에는 ArrayDeque를 사용하여 코드를 짰지만 시간 제한 0.5를 넘기는 문제가 발생했다.
- **개선 방법**
  1. ArrayDeque 대신 LinkedList 사용. pollLast(), getLast() 연산을 자주 사용하면 LinkedList가 더 적합하다.
  2. split(" ") 대신 substring() 이용. split()은 내부적으로 배열을 생성하는 비용이 크다.
  3. BufferedReader 사용. Scanner는 동기화 처리가 되어 있어 속도가 느리다.