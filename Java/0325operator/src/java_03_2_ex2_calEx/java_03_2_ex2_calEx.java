package java_03_2_ex2_calEx;
import java.util.Scanner;
import java.util.Random;

public class java_03_2_ex2_calEx {
    public static void main(String[] args) {
        //operatorExample();
        //conditionExample();
        //repeatExample();
        referenceExample();
    }

    public static void operatorExample() {
        int x = 5;
        int y = 10;
        String answer = "x + y = " + (x + y);
        System.out.println(answer);

        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        if ((number % 3) == 0)
            System.out.println(number + "는 3의 배수가 맞습니다.");
        else
            System.out.println(number + "는 3의 배수가 아닙니다.");

        System.out.println("점궤를 확인하세요");
        System.out.println("이름을 입력해주세요");

        String name = sc.next();

        System.out.println("나이를 입력해주세요");

        String ageString = sc.next();
        int age = Integer.parseInt(ageString);

        int fortune = new Random().nextInt(4);
        fortune++;

        System.out.println("점괘가 나왔습니다.");
        System.out.println(age + "살의 " + name + "씨, 당신의 운세 번호는 " +  fortune + " 입니다.");
        System.out.println("1: 대박 2: 중박 3: 보통 4: 망");
    }

    public static void conditionExample() {
        int score = 85;
        if(score >= 90) {
            System.out.println("점수가 90 이상입니다.");
        }
        else if(score >= 80) {
            System.out.println("점수가 90~80 입니다.");
        }
        else {
            System.out.println("점수가 80 미만입니다.");
        }
    }

    public static void repeatExample() {
        Outer:
        for(char upper = 'A'; upper <= 'Z'; upper++) {
            for(char lower = 'a'; lower <= 'z'; lower++) {
                System.out.println(upper + "-" + lower);
                if(lower == 'g') {
                    break Outer; // 바깥 for 문 마저 빠져나온다.
                }
            }
        }

        for(int i =0; i <= 10; i++) {
            if(i % 2 != 0) {
                continue;
            }
            System.out.println(i + "은 짝수");
        }

        Scanner sc = new Scanner(System.in);

        /*System.out.println("\n[예제 1]");

        System.out.print("국어 점수를 입력하세요: ");
        int korScore = sc.nextInt();
        System.out.print("영어 점수를 입력하세요: ");
        int engScore = sc.nextInt();
        System.out.print("수학 점수를 입력하세요: ");
        int mathScore = sc.nextInt();

        int averageScore = (korScore + engScore + mathScore) / 3;

        if(korScore >= averageScore) {
            System.out.println("국어 점수는 평균 이상입니다.");
        }
        else {
            System.out.println("국어 점수는 평균 미만입니다.");
        }

        if(engScore >= averageScore) {
            System.out.println("영어 점수는 평균 이상입니다.");
        }
        else {
            System.out.println("영어 점수는 평균 미만입니다.");
        }

        if(mathScore >= averageScore) {
            System.out.println("수학 점수는 평균 이상입니다.");
        }
        else {
            System.out.println("수학 점수는 평균 미만입니다.");
        }

        System.out.println("\n[예제 2]");

        int sumOdd =0;
        int sumEven =0;

        for(int i=0; i <= 100; i++) {
            if(i % 2 == 0) {
                sumEven += i;
            }
            else {
                sumOdd += i;
            }
        }

        System.out.println("홀수의 합: " + sumOdd);
        System.out.println("짝수의 합: " + sumEven);

        System.out.println("\n[예제 3]");

        for(int m=1; m <= 9; m++) {
            for(int n=1; n <= 9; n++) {
                System.out.println(m + "*" + n + "=" + (m * n));
            }
        }

        System.out.println("\n[예제 4]");

        int dice1 = (int) (Math.random() * 4) + 1;
        int dice2 = (int) (Math.random() * 4) + 1;

        System.out.println("(" + dice1 + "," + dice2 + ")");

        while ((dice1 + dice2) != 5) {
            dice1 = (int) (Math.random() * 5) + 1;
            dice2 = (int) (Math.random() * 5) + 1;

            System.out.println("(" + dice1 + "," + dice2 + ")");
        }*/

        System.out.println("\n[예제 5]");
        for(int x=0; x <= 10; x++) {
            for(int y=0; y <= 10; y++) {
                if( 4 * x + 5 * y == 60) {
                    System.out.println("(" + x + "," + y + ")");
                }
            }
        }

        System.out.println("\n[예제 6]");

        int menuNo;

        MENU:
        while (true) {
            System.out.println("[메뉴] 1: 검색, 2: 등록, 3: 삭제, 4: 변경, 5: 종료>");
            menuNo = sc.nextInt();

            switch(menuNo) {
                case 1:
                    System.out.println("검색합니다");
                    break;
                case 2:
                    System.out.println("등록합니다");
                    break;
                case 3:
                    System.out.println("삭제합니다");
                    break;
                case 4:
                    System.out.println("변경합니다");
                    break;
                case 5:
                    System.out.println("종료합니다");
                    break MENU;
                default:
                    System.out.println("잘못된 입력입니다.");
            }

        }
    }

    public static void referenceExample() {
        int[] arr1;
        int[] arr2;
        int[] arr3;

        arr1 = new int[] {1, 2, 3};
        arr2 = new int[] {1, 2, 3};
        arr3 = arr2;

        System.out.println(arr1);
        System.out.println(arr2);
        System.out.println(arr3);

        String hobby = "여행";
        System.out.println(System.identityHashCode(hobby));
        System.out.println(System.identityHashCode("여행"));

        int[] score = new int[] {1, 2, 3};
        String[] strings = new String[3];
        System.out.println(strings[0]);
    }
}
