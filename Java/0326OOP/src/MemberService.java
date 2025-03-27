public class MemberService {
    Member user = new Member("홍길동", "hong", "12345");

    /*
    public boolean login(String id, String password) {
        if (id == "hong" && password == "12345")
            return true;
        else
            return false;
    }
    */
    public boolean login(String id, String password) {
        if (id == user.id && password == user.password)
            return true;
        else
            return false;
    }

    public void logout(String id) {
        System.out.println(id + " 님이 로그아웃 되었습니다.");

    }
}