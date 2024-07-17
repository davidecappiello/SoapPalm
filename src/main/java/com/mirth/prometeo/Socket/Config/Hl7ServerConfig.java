package com.mirth.prometeo.Socket.Config;

import com.mirth.prometeo.Socket.Service.HL7SocketServerService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Hl7ServerConfig {

    private final HL7SocketServerService hl7SocketServerService;

    public Hl7ServerConfig(HL7SocketServerService hl7SocketServerService) {
        this.hl7SocketServerService = hl7SocketServerService;
    }

    @PostConstruct
    public void init() {
        hl7SocketServerService.startServer(32010);
    }

}
