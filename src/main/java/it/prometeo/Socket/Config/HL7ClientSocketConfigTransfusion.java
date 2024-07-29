package it.prometeo.Socket.Config;

import it.prometeo.Socket.Handler.PalmWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class HL7ClientSocketConfigTransfusion implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new PalmWebSocketHandler(), "/websocket-endpoint")
                .setAllowedOrigins("*");
    }
}
