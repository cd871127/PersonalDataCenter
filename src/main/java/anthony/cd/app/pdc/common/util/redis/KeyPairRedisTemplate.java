package anthony.cd.app.pdc.common.util.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Repository;

import java.security.KeyPair;

@Repository
public class KeyPairRedisTemplate extends AbstractRedisTemplate<KeyPair> {
    public KeyPairRedisTemplate() {
        super();
    }

    public KeyPairRedisTemplate(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    

}
