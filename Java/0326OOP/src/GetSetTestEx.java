public class GetSetTestEx {
    public static void main(String[] args) {
        GetSetTest gettaSetta = new GetSetTest();

        gettaSetta.setName("Porsche");
        gettaSetta.setSpeed(180);
        gettaSetta.setStop(false);

        System.out.println("이름: " + gettaSetta.getName());
        System.out.println("현재 속도: " + gettaSetta.getSpeed());
        System.out.println("멈춤 여부: " + gettaSetta.isStop());
    }
}
