package systemclass;

public class KeyInExam {
    public static void main(String[] args) throws Exception{
        int keyCode = 0;

        while(true) {
            if (keyCode != 13 && keyCode != 10) { // Enter키와 Ctrl+Z키를 제외한 모든 키
                System.out.println("입력 받은 keyCode 값: " + keyCode);
                if(keyCode == 49) { // 1
                    break;
                }
            }
            keyCode = System.in.read();
        }
        System.out.println("프로그램 종료");
    }
}
