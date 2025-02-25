# BOJ 7000번 ~ 7999번 문제 풀이

## 📌 BOJ 5430 - AC
- **문제 링크**: [백준 5430번](https://www.acmicpc.net/problem/5430)
- **풀이 요약**: deque를 사용해 R이 등장할 때마다 플래그를 on/off 하여 pollFirst()나 pollLast()를 호출한다.
- **예외 처리**: substring을 하는 부분에서 빈 배열이 들어올 때 인덱스를 넝머 접근하는 경우가 있어 런타임에러가 발생했다.
  - 이 경우에는 들어온 token의 길이를 재 조건문으로 분기한 뒤 빈 배열이 들어왔을 때는 StringTokenizer에 빈 문자열만 전달한다.

## 📌 BOJ  -
- **문제 링크**:
- **풀이 요약**: 