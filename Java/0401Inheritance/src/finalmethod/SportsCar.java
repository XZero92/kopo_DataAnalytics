package finalmethod;

public class SportsCar extends Car {
    @Override
    public void speedup() {
        speed += 60;
    }

    /*@Override
    public void stop() {
        System.out.println("스포츠카를 정지합니다.");
        speed = 0;
    }*/
}
