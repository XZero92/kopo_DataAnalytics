package supertest;

public class SupersonicAirplane extends Airplane {
    enum FlyMode {
        NORMAL, SUPERSONIC
    }

    private FlyMode flyMode = FlyMode.NORMAL;

    public void fly() {
        if (flyMode == FlyMode.NORMAL) {
            super.fly();
        } else if (flyMode == FlyMode.SUPERSONIC) {
            System.out.println("초음속 비행합니다.");
        }
    }
    public void setFlyMode(FlyMode flyMode) {
        this.flyMode = flyMode;
    }

    public static void main(String[] args) {
        SupersonicAirplane ssa = new SupersonicAirplane();

        ssa.takeOff();
        ssa.fly();
        ssa.setFlyMode(FlyMode.SUPERSONIC);
        ssa.fly();
        ssa.setFlyMode(FlyMode.NORMAL);
        ssa.fly();
        ssa.land();
    }
}
