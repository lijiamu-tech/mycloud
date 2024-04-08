package com.beginner.common.util.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TokenGenerator  {

    public static String generateToken() {
        // 生成随机的 UUID
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        // 将 UUID 转换为字符串并去掉其中的 "-"
        String uuidStr = uuid.toString().replaceAll("-", "");

        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime);

        // 将当前时间格式化为"yyyyMMdd"格式的字符串
        String timeStr = currentTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 将当前时间和 UUID 组合成新的字符串
        String token = timeStr + uuidStr;
        System.out.println(token);
        return token;
    }

}


