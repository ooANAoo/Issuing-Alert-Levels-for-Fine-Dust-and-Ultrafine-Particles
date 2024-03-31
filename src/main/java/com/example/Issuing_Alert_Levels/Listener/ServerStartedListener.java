package com.example.Issuing_Alert_Levels.Listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
@Component
public class ServerStartedListener implements ApplicationListener<ApplicationReadyEvent> {

    private final WebApplicationContext webApplicationContext;

    public ServerStartedListener(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();

            System.out.println("서버가 시작되었습니다. 접속 주소: http://" + hostname + ":");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}