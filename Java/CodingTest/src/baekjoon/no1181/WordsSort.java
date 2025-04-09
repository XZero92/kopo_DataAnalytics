package baekjoon.no1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WordsSort {
    /*
    [문제]
    알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.
        1.길이가 짧은 것부터
        2.길이가 같으면 사전 순으로
    단, 중복된 단어는 하나만 남기고 제거해야 한다.

    [입력]
    첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000)
    둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다.
    주어지는 문자열의 길이는 50을 넘지 않는다.

    [출력]
    조건에 따라 정렬하여 단어들을 출력한다
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 입력 N
        int N = Integer.parseInt(br.readLine());
        Set<String> unorderedWords = new HashSet<>();

        // N 개의 단어를 Set에 삽입
        for (int i = 0; i < N; i++) {
            unorderedWords.add(br.readLine());
        }

        // 중복이 제거된 단어들을 List에 삽입 후 정렬
        List<String> orderedWords = new ArrayList<>(unorderedWords);
        orderedWords.sort(new WordComparator());

        // 정렬 완료된 단어 목록을 출력
        StringBuilder sb = new StringBuilder();
        for (String word : orderedWords) {
            sb.append(word).append("\n");
        }
        System.out.print(sb);
    }
}

// 문자열 비교를 위한 Comparator
// 길이가 짧은 것은 앞으로
// 길이가 같을 경우 사전 순으로
class WordComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        if (a.length() != b.length()) {
            return Integer.compare(a.length(), b.length());
        }
        return a.compareTo(b);
    }
}
