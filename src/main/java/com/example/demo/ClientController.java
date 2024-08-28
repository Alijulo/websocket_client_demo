package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

    private final MyWebSocketClient client;
    @Autowired
    public ClientController(MyWebSocketClient client){
        this.client=client;
    }

    @PostMapping("/message")
    public void message(@RequestBody MessagingDto message) throws InterruptedException {

        if(client.isClientClosed()){
            throw new RuntimeException("System is not connected");
        }
        System.out.println("client status: "+client.isClosed());
        client.send(String.valueOf(message));
    }

}

