package com.walcfpw.department.configuration;

import com.walcfpw.department.repository.entity.DepartmentEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfiguration {
    @Bean
    public ReactiveRedisTemplate<String, DepartmentEntity> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<DepartmentEntity> serializer = new Jackson2JsonRedisSerializer<>(DepartmentEntity.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, DepartmentEntity> builder =RedisSerializationContext.newSerializationContext(new Jackson2JsonRedisSerializer<>(String.class));
        RedisSerializationContext<String, DepartmentEntity> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

//    @Bean
//    public ReactiveHashOperations<String, Long, DepartmentEntity> hashOperations(ReactiveRedisConnectionFactory redisConnectionFactory) {
//        var template = new ReactiveRedisTemplate<>(
//                redisConnectionFactory,
//                RedisSerializationContext.<String, DepartmentEntity>newSerializationContext(new StringRedisSerializer())
//                        .hashKey(new GenericToStringSerializer<>(Long.class))
//                        .hashValue(new Jackson2JsonRedisSerializer<>(DepartmentEntity.class))
//                        .build());
//        return template.opsForHash();
//    }
////
//    @Bean
//    ReactiveRedisOperations<String, DepartmentEntity> redisOperations(ReactiveRedisConnectionFactory factory) {
//        Jackson2JsonRedisSerializer<DepartmentEntity> serializer = new Jackson2JsonRedisSerializer<>(DepartmentEntity.class);
//
//        RedisSerializationContext.RedisSerializationContextBuilder<String, DepartmentEntity> builder =
//                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
//
//        RedisSerializationContext<String, DepartmentEntity> context = builder.value(serializer).build();
//
//        return new ReactiveRedisTemplate<>(factory, context);
//    }


}