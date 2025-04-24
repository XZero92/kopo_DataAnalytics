package com.example.servlet_0424;

import jakarta.servlet.ServletContextListener;

public class ContextListenerEx implements ServletContextListener {
    long initTime;
    long endTime;

    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
        // ServletContext 초기화 시 호출되는 메서드
        System.out.println("ServletContext 초기화됨");
        // 초기화 순간의 시간을 저장 및 출력
        initTime = System.currentTimeMillis();
        System.out.println("ServletContext 초기화 시간: " + initTime);
        // 초기화 작업 수행
        //sce.getServletContext().setAttribute("appName", "MyApp");
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(jakarta.servlet.ServletContextEvent sce) {
        // ServletContext 소멸 시 호출되는 메서드
        System.out.println("ServletContext 소멸됨");
        // 소멸 순간의 시간을 저장 및 출력
        endTime = System.currentTimeMillis();
        System.out.println("ServletContext 소멸 시간: " + endTime);
        // 초기화와 소멸 사이의 시간 차이를 계산
        long duration = endTime - initTime;
        System.out.println("ServletContext가 " + duration + "ms 동안 유지됨");
        // 정리 작업 수행
        ServletContextListener.super.contextDestroyed(sce);
    }

}
