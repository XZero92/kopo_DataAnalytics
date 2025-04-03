package java_19_7_ex4_alert;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class IOEx {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("프로그램 시작");

        FileReader fr = new FileReader("notexist.txt");
    }
}
