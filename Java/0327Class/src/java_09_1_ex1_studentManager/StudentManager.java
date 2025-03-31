package java_09_1_ex1_studentManager;

import java.util.Scanner;

public class StudentManager {
    private Scanner sc = new Scanner(System.in);
    private Student[] students = new Student[100];

    public static void main(String[] args) {
        StudentManager app = new StudentManager();
        app.run();
    }

    public void run() {
        programLoop:
        while(true) {
            System.out.println("-------------------------------------------------------");
            System.out.println("1.학생 추가 | 2.학생 목록 | 3.학생 수정 | 4.학생 삭제 | 5.종료");
            System.out.println("-------------------------------------------------------");
            System.out.print("선택 > ");

            String menuIdx = sc.nextLine();

            switch(menuIdx) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    showStudents();
                    break;
                case "3":
                    modifyStudent();
                    break;
                case "4":
                    deleteStudent();
                    break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    break programLoop;
                default:
                    System.out.println("잘못된 선택입니다.");
                    continue;
            }
        }
    }

    public void addStudent() {
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
        students[currentIdx].setAge(Integer.parseInt(sc.nextLine()));
        System.out.print("전공: ");
        students[currentIdx].setMajor(sc.nextLine());

        System.out.println("학생을 추가했습니다.");
        return;
    }

    public void showStudents() {
        System.out.println("---------");
        System.out.println("학생 목록");
        System.out.println("---------");
        for (int i = 0; i < students.length; i++) {
            if(students[i] != null) {
                System.out.println(students[i].getName() + "\t" + students[i].getAge() +
                        "\t"  + students[i].getKey() + "\t" + students[i].getMajor());
            }
        }
        System.out.println("------------------------------------------------------");
    }

    public void modifyStudent() {
        System.out.println("---------");
        System.out.println("학생 수정");
        System.out.println("---------");
        System.out.print("수정할 학생의 학번(key): ");
        String key = sc.nextLine();

        for (int i = 0; i < students.length; i++) {
            if(students[i] != null && students[i].getKey().equals(key)) {
                System.out.print("이름: ");
                students[i].setName(sc.nextLine());
                System.out.print("나이: ");
                students[i].setAge(Integer.parseInt(sc.nextLine()));
                System.out.print("전공: ");
                String mjr = sc.nextLine();
                students[i].setMajor(mjr);

                System.out.println("학생 정보를 수정했습니다.");
                return;
            }
        }
        System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
    }

    public void deleteStudent() {
        System.out.println("---------");
        System.out.println("학생 삭제");
        System.out.println("---------");
        System.out.print("삭제할 학생의 학번(key): ");
        String key = sc.nextLine();

        for (int i = 0; i < students.length; i++) {
            if(students[i] != null && students[i].getKey().equals(key)) {
                students[i] = null;
                System.out.println("학생 정보를 삭제했습니다.");
                return;
            }
        }
        System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
    }

}
