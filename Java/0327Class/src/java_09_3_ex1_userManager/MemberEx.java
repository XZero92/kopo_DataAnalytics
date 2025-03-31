package java_09_3_ex1_userManager;
import java.util.Scanner;

public class MemberEx {

    private Member[] members = new Member[100];
    private Member loggedInMember;
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MemberEx app = new MemberEx();
        app.run();
    }

    public void run() {
        programLoop:
        while (true) {
            showMenu();
            System.out.print("메뉴 선택 > ");
            String menuIdx = sc.nextLine();

            switch (menuIdx) {
                case "1":
                    addMember();
                    break;
                case "2":
                    deleteMember();
                    break;
                case "3":
                    showMembers();
                    break;
                case "4":
                    loginMember();
                    break;
                case "5":
                    logoutMember();
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다.");
                    break programLoop;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void showMenu() {
        if (loggedInMember != null) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("로그인한 사용자: " + loggedInMember.getName());
            System.out.println("----------------------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------------------");
            System.out.println("로그인하지 않은 상태입니다.");
            System.out.println("----------------------------------------------------------------");
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("1.회원 추가 | 2.회원 삭제 | 3.회원 조회 | 4.로그인 | 5.로그아웃 | 6.종료");
        System.out.println("----------------------------------------------------------------");
    }

    public void addMember() {
        System.out.println("-------");
        System.out.println("회원 추가");
        System.out.println("-------");

        String id;
        while (true) {
            System.out.print("ID: ");
            id = sc.nextLine();
            if (id.isEmpty()) {
                System.out.println("ID를 입력하세요.");
                continue;
            }
            if (id.length() < 5 || id.length() > 15) {
                System.out.println("ID는 5자 이상, 15자 이하로 입력해야 합니다.");
                continue;
            }
            if (!id.matches("^[a-zA-Z0-9]*$")) {
                System.out.println("ID에는 숫자와 알파벳 대문자/소문자만 사용 가능 합니다.");
                continue;
            }
            boolean idExists = false;
            for (int i = 0; i < members.length; i++) {
                if (members[i] != null && members[i].getId().equals(id)) {
                    idExists = true;
                    break;
                }
            }
            if (idExists) {
                System.out.println("이미 존재하는 ID입니다.");
                continue;
            }

            break;
        }

        String password;
        while (true) {
            System.out.print("PW: ");
            password = sc.nextLine();
            if (password.isEmpty()) {
                System.out.println("비밀번호를 입력하세요.");
                continue;
            }
            if (password.length() < 5 || password.length() > 15) {
                System.out.println("비밀번호는 5자 이상, 15자 이하로 입력해야 합니다.");
                continue;
            }
            if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
                System.out.println("비밀번호에는 숫자, 알파벳 대문자, 소문자가 각각 하나 이상이 포함되어야합니다.");
                continue;
            }
            break;
        }

        System.out.print("이름: ");
        String name = sc.nextLine();

        String emailAddress;
        while (true) {
            System.out.print("이메일 주소: ");
            emailAddress = sc.nextLine();
            if (emailAddress.isEmpty()) {
                System.out.println("이메일 주소를 입력하세요.");
                continue;
            }
            if (!emailAddress.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("잘못된 이메일 주소입니다.");
                continue;
            }
            break;
        }

        String phoneNumber;
        while (true) {
            System.out.print("전화번호: ");
            phoneNumber = sc.nextLine();
            if (phoneNumber.isEmpty()) {
                System.out.println("전화번호를 입력하세요.");
                continue;
            }
            if (!phoneNumber.matches("^\\d{3}-\\d{3,4}-\\d{4}$")) {
                System.out.println("전화번호는 000-0000-0000 형식으로 입력해야 합니다.");
                continue;
            }
            break;
        }

        int currentIdx;
        for (currentIdx = 0; currentIdx < members.length; currentIdx++) {
            if (members[currentIdx] == null) {
                members[currentIdx] = new Member(id, password, name, emailAddress, phoneNumber);
                System.out.println("회원 추가가 완료되었습니다.");
                return;
            }
        }
        System.out.println("목록이 가득 찼습니다.");
        return;
    }

    public void deleteMember() {
        System.out.println("-------");
        System.out.println("회원 삭제");
        System.out.println("-------");
        System.out.print("삭제할 ID: ");
        String id = sc.nextLine();

        for(int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].getId().equals(id)) {
                System.out.println("정말 삭제하시겠습니까? (y/n)");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    members[i] = null;
                    System.out.println("회원 삭제가 완료되었습니다.");
                    return;
                } else {
                    System.out.println("회원 삭제가 취소되었습니다.");
                    return;
                }
            }
        }
        System.out.println("해당 ID를 가진 회원이 존재하지 않습니다.");
    }

    public void showMembers() {
        System.out.println("-------");
        System.out.println("회원 조회");
        System.out.println("-------");
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null) {
                System.out.println("ID: " + members[i].getId());
                System.out.println("이름: " + members[i].getName());
                System.out.println("이메일: " + members[i].getEmailAddress());
                System.out.println("전화번호: " + members[i].getPhoneNumber());
                System.out.println("--------------------");
            }
        }
    }

    public void loginMember() {
        if (loggedInMember != null) {
            System.out.println("이미 로그인 상태입니다.");
            return;
        }

        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("PW: ");
        String password = sc.nextLine();

        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].getId().equals(id) && members[i].getPassword().equals(password)) {
                loggedInMember = members[i];
                System.out.println("로그인 성공");
                return;
            }
        }
        System.out.println("로그인 실패");
    }

    public void logoutMember() {
        if (loggedInMember != null) {
            loggedInMember = null;
            System.out.println("로그아웃 성공");
        } else {
            System.out.println("로그인 상태가 아닙니다.");
        }
    }
}
