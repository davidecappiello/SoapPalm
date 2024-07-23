package com.mirth.prometeo.Socket.Config;

import com.mirth.prometeo.HL7Config;
import com.mirth.prometeo.Socket.Service.HL7SocketClientService;
import com.mirth.prometeo.Socket.Service.HL7SocketServerService;
import com.mirth.prometeo.Util;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Hl7ServerConfig {

    private final HL7SocketServerService hl7SocketServerService;
    private static HL7Config hl7Config = null;

    @Autowired
    public Hl7ServerConfig(HL7SocketServerService hl7SocketServerService, HL7Config hl7Config) {
        this.hl7SocketServerService = hl7SocketServerService;
        Hl7ServerConfig.hl7Config = hl7Config;
    }

    @PostConstruct
    public void init() {
        Thread socketServerThread = new Thread(() -> hl7SocketServerService.startServer(hl7Config.getSocketServerPort()));
        socketServerThread.start();
    }

}
