package java_09_1_ex1_studentManager;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    Scanner sc = new Scanner(System.in);
    Student[] students = new Student[100];

    void showMenu() {
        System.out.println("-------------------------------------------------------");
        System.out.println("1.학생 추가 | 2.학생 목록 | 3.학생 수정 | 4.학생 삭제 | 5.종료");
        System.out.println("-------------------------------------------------------");
    }

    void addStudent() {
        int currentIdx;
        for(currentIdx = 0; currentIdx < students.length; currentIdx++) {
            if(students[currentIdx] == null) {
                break;
            }
        }
        if(currentIdx >= students.length) {
            System.out.println("목록이 가득 찼습니다.");
            return;
        }
        System.out.println("---------");
        System.out.println("학생 추가");
        System.out.println("---------");

        students[currentIdx] = new Student();

        System.out.print("학번(key): ");
        students[currentIdx].setKey(sc.nextLine());
        System.out.print("이름: ");
        students[currentIdx].setName(sc.nextLine());
        System.out.print("나이: ");
        students[currentIdx].setAge(sc.nextInt());
        System.out.print("전공: ");
        students[currentIdx].setMajor(sc.nextLine());

        System.out.println("학생을 추가했습니다.");
        return;
    }

    void showStudents() {
        System.out.println("---------");
        System.out.println("학생 추가");
        System.out.println("---------");
        for (int i = 0; i < students.length; i++) {
            if(students[i] != null) {
                System.out.println(students[i].toString());
            }
        }
    }

}
