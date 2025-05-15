package kr.co.tlf.ex.jsp_final_ecommerce_ysh.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public class ShortUUID {
    public static String getShortUUID() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());

        // URL 안전한 Base64 인코딩하며 패딩 문자는 제거합니다.
        return Base64.getUrlEncoder().withoutPadding().encodeToString(buffer.array());
    }
}