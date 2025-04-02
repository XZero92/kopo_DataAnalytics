package java_17_1_ex1_robotexam;

public class PoohImpl implements ArmLeg {
    @Override
    public void introduce() {
        System.out.println("곰돌이 입니다.");
        this.move();
        System.out.println("==========================");
    }
    @Override
    public void move() {
        System.out.println("팔다리를 움직일 수 있습니다.");
    }
}
