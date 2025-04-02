package java_17_1_ex1_robotexam;

public class AirplaneImpl implements Missile, Light {
    @Override
    public void introduce()  {
        System.out.println("비행기 입니다.");
        this.fire();
        this.turnOnLight();
        System.out.println("==========================");
    }
    @Override
    public void fire() {
        System.out.println("미사일 발사 가능합니다.");
    }
    @Override
    public void turnOnLight() {
        System.out.println("불빛 발사 가능합니다.");
    }
}
