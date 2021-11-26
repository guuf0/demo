package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    private TestComponent test;

    @Resource
    TestComponent testComponent;

    @GetMapping("/test")
    public void test() {
        System.out.println("Get");
        testComponent.printLine();
    }


}
