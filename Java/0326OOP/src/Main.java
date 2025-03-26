import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        // arrayExample();
    }
    public static void arrayExample() {
        int[] scores = {83, 90, 87, 85, 95};
        int[] b;
        b = scores;

        b[0] = 100;
        System.out.println(scores[0]);

        System.out.println("[예제 1]");
        int[][] array1 = {
                {95, 86},
                {83, 92, 96},
                {78, 83, 93, 87, 88}
        };
        String name1 ="홍길동";
        name1 = null;

        System.out.println(array1.length);
        System.out.println(array1[2].length);

        System.out.println("[예제 2]");
        int[] points = new int[4];
        double[] weights = new double[5];
        boolean[] answers = new boolean[3];
        String[] names = new String[3];

        System.out.println("[예제 3]");

        int[] array2 = {1, 5, 3, 8, 2};

        int max = 0;
        for(int i: array2) {
            if(i > max) {
                max = i;
            }
        }
        System.out.println("최대값은 " + max);

        System.out.println("[예제 4]");

        int[][] array3 = {
                {95, 86},
                {83, 92, 96},
                {78, 83, 93, 87, 88}
        };
        int arr3Sum = 0;
        int arr3Avg = 0;
        int arr3Count = 0;
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                arr3Sum += array3[i][j];
                arr3Count++;
            }
        }
        arr3Avg = arr3Sum / arr3Count;

        System.out.println("총합: " + arr3Sum);
        System.out.println("평균: " + arr3Avg);


        System.out.println("[예제 5]");

        int[] moneyList = {1593200, 57800, 193500};

        System.out.println("for 문 사용");
        for (int i = 0; i < moneyList.length; i++) {
            System.out.print(moneyList[i] + " ");
        }
        System.out.println("\nfor each 문 사용");
        for (int money: moneyList) {
            System.out.print(money + " ");
        }

        System.out.println("[예제 6]");
        int[] count = null;
        double[] heights = {191.3, 185.0};

        // NullPointerException
        //System.out.println(count[0]);

        // ArrayIndexOutOfBoundsException
        //System.out.println(heights[2]);

        System.out.println("[예제 7]");
        Scanner sc = new Scanner(System.in);

        int studentNum = 0;
        int[] studentsScore = new int[0];
        int scoreAvg = 0;
        int scoreHighest = 0;

        MENU:
        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
            System.out.println("------------------------------------------------");
            System.out.print("메뉴 선택> ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("학생 수를 입력하세요: ");
                    studentNum = sc.nextInt();
                    if (studentNum < 10) {
                         System.out.println("학생 수는 최소 10명 이상이어야 합니다.");
                         break;
                    }
                    else {
                        studentsScore = null;
                        studentsScore = new int[studentNum];
                        System.out.println("학생 수 " + studentNum + "이 기록되었습니다.");
                        break;
                    }
                case 2:
                    if (studentNum < 10) {
                        System.out.println("학생 수는 최소 10명 이상이어야 합니다.");
                        break;
                    }
                    else {
                        for(int n = 0; n < studentNum; n++) {
                            System.out.print((n+1) + "번째 학생의 점수를 입력하세요 : ");
                            studentsScore[n] = sc.nextInt();
                        }
                        System.out.println("점수 입력이 종료되었습니다.");
                        break;
                    }
                case 3:
                    if(studentsScore.length < 10) {
                        System.out.println("학생 목록이 작성되지 않았습니다.");
                        break;
                    }
                    else {
                        for(int n = 0; n < studentNum; n++) {
                            System.out.println((n + 1) + ": " + studentsScore[n]);
                        }
                        break;
                    }
                case 4:
                    if(studentsScore.length < 10) {
                        System.out.println("학생 목록이 작성되지 않았습니다.");
                        break;
                    }
                    else {
                        int sum = 0;

                        for(int m: studentsScore) {
                            sum += m;

                            if (scoreHighest < m)
                                scoreHighest = m;
                        }
                        scoreAvg = sum / studentsScore.length;

                        System.out.println("평균: " + scoreAvg);
                        System.out.println("최고점: " + scoreHighest);
                        break;
                    }
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    break MENU;
                default:
                    System.out.println("유효하지 않은 명령입니다.");
                    continue;
            }
        }


    }

}