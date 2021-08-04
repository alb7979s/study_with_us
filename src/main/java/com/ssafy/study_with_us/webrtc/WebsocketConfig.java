//package com.ssafy.study_with_us.webrtc;
//
//import org.kurento.client.KurentoClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.*;
//import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
//
//@Configuration
//@EnableWebSocket
//public class WebsocketConfig implements WebSocketConfigurer {
//    @Bean
//    public UserRegistry registry() {
//        return new UserRegistry();
//    }
//
//    @Bean
//    public RoomManager roomManager() {
//        return new RoomManager();
//    }
//
//    @Bean
//    public CallHandler groupCallHandler() {
//        return new CallHandler();
//    }
//
//    @Bean
//    public KurentoClient kurentoClient() {
//        return KurentoClient.create();
//    }
//
//    @Bean
//    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(32768);
//        return container;
//    }
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(groupCallHandler(), "/groupcall").setAllowedOriginPatterns("*");
//    }
//
//}
