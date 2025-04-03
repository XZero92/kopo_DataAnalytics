package java_19_1_ex1_exception;

public class ExceptionEx1 {
    public static void main(String[] args) {
        String s = null;
        try {
            int length = s.length();
        } catch (NullPointerException e) {
            System.out.println("NullPointer 예외를 catch 하였습니다.");
            System.out.println("[Stack Trace 시작]");
            e.printStackTrace();
            System.out.println("[Stack Trace 종료]");
        }
    }
}
