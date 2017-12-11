package anthony.cd.app.pdc.message.socket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    @Value("${pdc.udp-port}")
    private Integer port;

    public void test() {
        System.out.println(port);
    }
}
