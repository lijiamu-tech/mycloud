package com.beginner.common.util.utils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisConnectionUtil {

    private static final String REDIS_HOST = "192.168.43.12";
    private static final int REDIS_PORT = 6379;

    private static RedisClient redisClient;
    private static StatefulRedisConnection<String, String> connection;
    private static RedisCommands<String, String> commands;

    // 获取 Redis 连接
    public static RedisCommands<String, String> getConnection() {
        if (commands == null || !connection.isOpen()) {
            redisClient = RedisClient.create("redis://" + REDIS_HOST + ":" + REDIS_PORT);
            connection = redisClient.connect();
            commands = connection.sync();
        }
        return commands;
    }

    // 关闭 Redis 连接
    public static void closeConnection() {
        if (connection != null && connection.isOpen()) {
            connection.close();
        }
        if (redisClient != null) {
            redisClient.shutdown();
        }
    }
}
