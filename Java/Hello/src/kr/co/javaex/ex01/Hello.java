package kr.co.javaex.ex01;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) { // 진입점
        System.out.println("[프로그램 시작]");
        System.out.println("Hello Java, welcome!");
        System.out.println("[프로그램 종료]");

        System.out.println("[예제 1]");
        System.out.println("(A): 컴파일러");
        System.out.println("(B): 인터프리터");
        System.out.println("(C): 소스 코드");
        System.out.println("(D): 바이트 코드");
        System.out.println("(E): JVM");

        System.out.println("[예제 2]");
        System.out.println("(3)-(1)-(2)-(4)");

        System.out.println("[예제 3]");
        System.out.println("(4)");

        System.out.println("[예제 4]");
        System.out.println("개발자가 되기 위한 필수 개발 언어 Java");

        String str = "10";
        byte value = Byte.parseByte(str);
        System.out.println(value);

        int value1 = Integer.parseInt("10");
        double value2 = Double.parseDouble("3.14");
        boolean value3 = Boolean.parseBoolean("true");

        System.out.println("value1: " + value1);
        System.out.println("value2: " + value2);
        System.out.println("value3: " + value3);

        String str1 = String.valueOf(10);
        String str2 = String.valueOf(3.14);
        String str3 = String.valueOf(true);

        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str3: " + str3);

        Scanner scnr = new Scanner(System.in);

        System.out.print("score 입력: ");
        String strScore = scnr.nextLine();
        System.out.println("입력 받은 score: " + strScore);

        int a = 3;
        int b = 5;
        int c = a * b;
        System.out.printf("가로 %d, 세로 %d,의 직사각형 면적은 %d", a, b, c);

        boolean bulin = true;
        char gender = '남';
        double piDouble = 3.14159;
        long piLong = 314159265853979L;
        String walkingDay = "오늘 하루 열심히 걸어, 9000 걸음을 채웠다.";
    }
}