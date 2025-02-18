# BOJ 11000번 ~ 11999번 문제 풀이

## 📌 BOJ 11866 - 요세푸스 문제0
- **문제 링크**: [백준 11866번](https://www.acmicpc.net/problem/11866)
- **풀이 요약**: LinkedList와 Iterator를 사용. iterator의 hasNext가 false이면, 마지막 요소를 가리키고 있으므로 list.iterator()를 호출해 다시 처음을 가리키도록 했다. 이후에 제거될 원소를 출력하고 remove 호출로 제거.