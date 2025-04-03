package Map;

import java.util.HashMap;
import java.util.Map;

public class Entries {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("김길동", 85);
        map.put("홍길동", 95);
        map.put("길홍이", 95);
        map.put("홍길동", 87);

        System.out.println("총 엔트리: " + map.size());

        int value = map.get("홍길동");
        System.out.println("value: " + value);
    }
}
