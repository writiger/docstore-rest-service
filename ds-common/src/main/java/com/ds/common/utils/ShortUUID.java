package com.ds.common.utils;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

/**
 * @author writiger
 * @description 生成22位短uuid，再保证不重复的前提下尽可能变短
 * @create_at 2024-03-12 14:05
 */
public class ShortUUID {
    public static String generateShortUUID(){
        // 生成32位UUID
        UUID uuid = UUID.randomUUID();
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());

        String base64String = Base64.getEncoder().encodeToString(byteBuffer.array());
        return base64String.substring(0,base64String.length()-2);
    }
}
