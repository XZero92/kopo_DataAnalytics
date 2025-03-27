public class Member {
    String name;
    String id;
    String password;
    int age;
    String telno;
    String address;

    Member(String name, String id) {
        this.name = name;
        this.id = id;
    }
    Member(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
}
