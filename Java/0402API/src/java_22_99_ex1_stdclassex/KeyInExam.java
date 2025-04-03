package java_22_99_ex1_stdclassex;

import java.io.IOException;

public class KeyInExam {
    public static void main(String[] args) throws IOException {
        int keyCode = 0;
        int speed = 0;

        while (true) {
            System.out.println("현재 속도: " + speed);
            System.out.println("1. 속도 감소 | 2. 속도 증가 | 3. 종료");
            keyCode = System.in.read();
            if (keyCode != 13 && keyCode != 10) { // Enter키와 Ctrl+Z키를 제외한 모든 키
                switch (keyCode) {
                    case 49:
                        speed--;
                        break;
                    case 50:
                        speed++;
                        break;
                    case 51:
                        System.out.println("프로그램 종료");
                        System.exit(0); // 종료
                        break;
                    default:
                        break;
                }
            }
            // Enter 키를 무시하기 위해 추가
            while (keyCode == 13 || keyCode == 10) {
                keyCode = System.in.read();
            }
        }
    }
}
