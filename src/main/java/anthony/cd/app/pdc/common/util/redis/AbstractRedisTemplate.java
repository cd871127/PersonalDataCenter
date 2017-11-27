package anthony.cd.app.pdc.common.util.redis;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class AbstractRedisTemplate<T> extends RedisTemplate<String, T> {
     AbstractRedisTemplate() {
        RedisSerializer<T> serializer = new Serializer<T>();
        setKeySerializer(serializer);
        setValueSerializer(serializer);
        setHashKeySerializer(serializer);
        setHashValueSerializer(serializer);
    }

    AbstractRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
