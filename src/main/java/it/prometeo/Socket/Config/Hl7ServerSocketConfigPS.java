package it.prometeo.Socket.Config;

import it.prometeo.Configuration.HL7Config;
import it.prometeo.Socket.Service.HL7SocketServerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Hl7ServerSocketConfigPS {

    private final HL7SocketServerService hl7SocketServerService;
    private static HL7Config hl7Config = null;

    @Autowired
    public Hl7ServerSocketConfigPS(HL7SocketServerService hl7SocketServerService, HL7Config hl7Config) {
        this.hl7SocketServerService = hl7SocketServerService;
        Hl7ServerSocketConfigPS.hl7Config = hl7Config;
    }

    @PostConstruct
    public void init() {
        Thread socketServerThread = new Thread(() -> hl7SocketServerService.startServer(hl7Config.getSocketServerPort()));
        socketServerThread.start();
    }

}
