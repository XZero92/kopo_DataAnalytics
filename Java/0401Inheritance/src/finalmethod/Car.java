package finalmethod;

public class Car {
    public int speed;

    public void speedup() {
        speed += 1;
    }

    public final void stop() {
        System.out.println("차를 정지합니다.");
        speed = 0;
    }
}
