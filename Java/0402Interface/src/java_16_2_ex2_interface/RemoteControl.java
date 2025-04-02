package java_16_2_ex2_interface;

public interface RemoteControl {
    public static final int MAX_VOLUME = 10; // 상수
    public static final int MIN_VOLUME = 0; // 상수

    void turnOn();
    void turnOff();
    void setVolume(int volume);
}
