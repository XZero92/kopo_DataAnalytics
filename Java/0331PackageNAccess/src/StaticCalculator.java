import java.util.Scanner;

public class StaticCalculator {

    public static int plus(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return (double) a / b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int a = sc.nextInt();
            String op = sc.next();
            int b = sc.nextInt();

            switch (op) {
                case "+":
                    System.out.println(plus(a, b));
                    break;
                case "-":
                    System.out.println(minus(a, b));
                    break;
                case "*":
                    System.out.println(multiply(a, b));
                    break;
                case "/":
                    System.out.println(divide(a, b));
                    break;
                default:
                    System.out.println("잘못된 연산자입니다.");
                    break;
            }
        }
    }
}
