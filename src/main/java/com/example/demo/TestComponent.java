package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestComponent {

    public TestComponent(){
        System.out.println("Constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Post construct");
    }

    public void printLine(){
        System.out.println("Line");
    }


    @PreDestroy
    public void dest() {
        System.out.println("Pre destroy");
    }
}
