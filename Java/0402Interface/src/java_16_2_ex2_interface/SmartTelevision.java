package java_16_2_ex2_interface;

public class SmartTelevision implements RemoteControl, Searchable {
    private int volume;

    public void turnOn() {
        System.out.println("스마트 TV를 켭니다");
    }

    public void turnOff() {
        System.out.println("스마트 TV를 끕니다");
    }

    public void setVolume(int volume) {
        if (volume > RemoteControl.MAX_VOLUME) {
            this.volume = RemoteControl.MAX_VOLUME;
        } else if (volume < RemoteControl.MIN_VOLUME) {
            this.volume = RemoteControl.MIN_VOLUME;
        } else {
            this.volume = volume;
        }
        System.out.println("현재 스마트 TV 볼륨: " + this.volume);
    }

    public void search(String url) {
        System.out.println(url + "을 검색합니다.");
    }
}
