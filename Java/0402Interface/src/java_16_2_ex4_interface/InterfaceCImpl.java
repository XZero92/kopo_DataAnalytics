package java_16_2_ex4_interface;

public class InterfaceCImpl implements C {
    public void methodA() {
        System.out.println("InterfaceCImpl-methodA() 실행");
    }
    public void methodB() {
        System.out.println("InterfaceCImpl-methodB() 실행");
    }
    public void methodC() {
        System.out.println("InterfaceCImpl-methodC() 실행");
    }

    public static void main(String[] args) {
        A iA = new InterfaceCImpl();
        B iB = new InterfaceCImpl();
        C iC = new InterfaceCImpl();

        // 인터페이스 A를 통한 호출
        iA.methodA();
        System.out.println();
        // 인터페이스 B를 통한 호출
        iB.methodB();
        System.out.println();
        // 인터페이스 C를 통한 호출
        iC.methodA();
        iC.methodB();
        iC.methodC();
    }
}
