package trycatch;

import java.io.FileWriter;
import java.io.IOException;

public class ExceptionExam {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("data.txt");
            fw.write("내 이름은 무엇");
        } catch (IOException e) {
            System.out.println("IOException 발생");
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String[] strArr = {"100", "2oo"};

        for(int i = 0; i < strArr.length; i++) {
            try {
                int iVal = Integer.parseInt(strArr[i]);
                System.out.println(iVal);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("배열 인덱스 초과 - " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("숫자 변환 오류 - " + e.getMessage());
            }
        }
    }
}
