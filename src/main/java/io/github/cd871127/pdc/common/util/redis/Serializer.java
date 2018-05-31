package io.github.cd871127.pdc.common.util.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Serializer<T> implements RedisSerializer<T> {

    public byte[] serialize(T t) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (bytes == null)
            return null;
        return bytes;
    }

    @SuppressWarnings("unchecked")
    public T deserialize(byte[] bytes) {
        T t = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
            t = (T) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return t;
    }
}
