package it.prometeo.Socket.Config;

import it.prometeo.Configuration.HL7Config;
import it.prometeo.Socket.Service.Hl7SocketServerServiceTransfusion;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Hl7ServerSocketConfigTransfusion {

    private final Hl7SocketServerServiceTransfusion hl7SocketServerServiceTransfusion;
    private static HL7Config hl7Config = null;

    @Autowired
    public Hl7ServerSocketConfigTransfusion(Hl7SocketServerServiceTransfusion hl7SocketServerServiceTransfusion, HL7Config hl7Config) {
        this.hl7SocketServerServiceTransfusion = hl7SocketServerServiceTransfusion;
        Hl7ServerSocketConfigTransfusion.hl7Config = hl7Config;
    }

    @PostConstruct
    public void init() {
        Thread socketServerThread = new Thread(() -> hl7SocketServerServiceTransfusion.startServer(hl7Config.getSocketServerPortTransfusion()));
        socketServerThread.start();
    }
}
