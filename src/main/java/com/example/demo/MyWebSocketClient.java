package com.example.demo;

import java.net.URI;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MyWebSocketClient extends WebSocketClient {

    private boolean clientClosed = true;

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @PostConstruct  //try connection when the server is initialized
    public void initializeConnection(){
        // This method will be called once the Spring context is initialized
        new Thread( this::connect).start();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        clientClosed=false;
        System.out.println("WebSocket Connection Opened");
        send("SERVERID:EMTECH004");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        clientClosed=true;
        new Thread(this::reconnect).start();
        System.out.println("WebSocket Connection Closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket Error: " + ex.getMessage());
    }

    @Override
    public void connect() {
        super.connect();
    }

    @Override
    public void reconnect() {
        super.reconnect();
    }

}
