package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class ClientConfig {
    @Bean
    public URI websocketUri() throws URISyntaxException{
        return new URI("ws://192.168.88.216:8080/iot-websocket");
    }

    @Bean
    public MyWebSocketClient myWebSocketClient(URI websocketUri){
        return new MyWebSocketClient(websocketUri);
    }
}
