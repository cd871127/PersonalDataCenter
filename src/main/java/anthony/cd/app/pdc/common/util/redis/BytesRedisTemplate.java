package anthony.cd.app.pdc.common.util.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BytesRedisTemplate extends AbstractRedisTemplate<byte[]> {
    public BytesRedisTemplate() {
        super();
    }

    public BytesRedisTemplate(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }



}
