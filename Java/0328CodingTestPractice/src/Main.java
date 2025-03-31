import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sugar = scanner.nextInt();

        int kg5count = sugar / 5; // 5kg 봉지 개수
        int kg5remain = sugar % 5; // 5kg 봉지를 제외한 설탕 무게
        int kg3count = kg5remain / 3; // 3kg 봉지 개수
        int kg3remain = kg5remain % 3; // 3kg 봉지를 제외한 설탕 무게


        for (; kg5count >= 0; kg5count--) { // 5kg 봉지 개수를 1씩 줄여가며 반복
            kg5remain = sugar - (kg5count * 5);
            kg3remain = kg5remain % 3; // 나머지를 딱 떨어지게 가져갈 수 있는가?
            if(kg3remain == 0) {
                kg3count = kg5remain / 3; // 3kg 봉지 개수
                break;
            }
        }

        if(kg3remain != 0) {
            System.out.println(-1);
        } else  {
            System.out.println(kg5count + kg3count);
        }
    }
}