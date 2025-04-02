package java_17_1_ex1_robotexam;

public class MazingerImpl implements Missile, ArmLeg{


    public MazingerImpl() {
        introduce();
        fire();
        move();
    }

    @Override
    public void introduce() {
        System.out.println("마징가 입니다.");
        this.fire();
        this.move();
        System.out.println("==========================");
    }
    @Override
    public void fire() {
        System.out.println("미사일 발사 가능합니다");
    }
    @Override
    public void move() {
        System.out.println("팔다리를 움직일 수 있습니다.");
    }
}
