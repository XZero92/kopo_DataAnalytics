public class GetSetTest {
    private String name;
    private int speed;
    private boolean stop;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpeed(int speed) {
        if(speed < 0) {
            this.speed = 0;
            return;
        } else
            this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isStop() {
        return stop;
    }
    public void setStop(boolean stop) {
        this.stop = stop;
    }

}
