package objclass;

public class EqualsExam {
    public static void main(String[] args) {
        Member obj1 = new Member("ㅎㄱㄷ");
        Member obj2 = new Member("ㅎㄱㅅ");
        Member obj3 = new Member("ㅎㄱㄷ");

        System.out.println("[obj1, obj2 주소 값 비교]");
        if(obj1 == obj2) { // 주소 값 비교
            System.out.println("obj1과 obj2는 동등합니다.");
        } else {
            System.out.println("obj1과 obj2는 동등하지 않습니다.");
        }

        System.out.println("\n[obj1, obj2 데이터 값 비교]");
        if(obj1.equals(obj2)) { // 데이터 비교
            System.out.println("obj1과 obj2는 동등합니다.");
        } else {
            System.out.println("obj1과 obj2는 동등하지 않습니다.");
        }

        System.out.println("\n[obj1, obj3 데이터 값 비교]");
        if(obj1.equals(obj3)) { // 데이터 비교
            System.out.println("obj1과 obj3는 동등합니다.");
        } else {
            System.out.println("obj1과 obj3는 동등하지 않습니다.");
        }

        System.out.println("\n[obj1, obj3 주소 값 비교]");
        if(obj1 == obj3) { // 주소 값 비교
            System.out.println("obj1과 obj3는 동등합니다.");
        } else {
            System.out.println("obj1과 obj3는 동등하지 않습니다.");
        }
    }
}
