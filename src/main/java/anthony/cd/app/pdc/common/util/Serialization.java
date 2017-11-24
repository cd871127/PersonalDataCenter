package anthony.cd.app.pdc.common.util;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Serialization {
    public byte[] serialize(Object object) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
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

    public Object unSerialize(byte[] bytes) {
        Object object = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
            object = ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return object;
    }
}
