//package com.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//public class RedisConfig
//{
//     @Bean
//     public RedisConnectionFactory connectionFactory()
//     {
//         return new LettuceConnectionFactory();
//     }
//
//     @Bean
//    public RedisTemplate<String, Object> redisTemplate()
//     {
//         return new RedisTemplate<>();
//     }
//}
