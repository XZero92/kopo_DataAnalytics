package baekjoon.no2839;

import java.util.*;

public class SugarDelivery {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int totalWeight = sc.nextInt();
        int bag3kgCount = 0; // 3kg 봉지 개수
        int bag5kgCount = totalWeight / 5; // 5kg 봉지 개수
        int remainWeight = totalWeight % 5; // 5kg 봉지로 채우고 남은 무게

        while(bag5kgCount >= 0) {
            if (remainWeight % 3 == 0) { // 남은 무게가 3kg 봉지로 나누어 떨어지면
                bag3kgCount = remainWeight / 3;

                System.out.println(bag5kgCount + bag3kgCount);
                return;
            }
            bag5kgCount--;
            remainWeight += 5;
        }

        System.out.println(-1); // 정확하게 totalWeight를 만들지 못한다면 -1 출력
    }
}
