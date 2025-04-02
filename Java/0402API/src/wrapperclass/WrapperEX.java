package wrapperclass;

public class WrapperEX {
    public static void main(String[] args) {
        int iVal1 = 49;

        Integer iObj = iVal1; // Boxing
        System.out.println(iObj);

        int value = iObj; // Unboxing
        System.out.println(value);

        int result = iObj + iVal1; // 연산 시 iObj Unboxing
        System.out.println(result);
    }
}
