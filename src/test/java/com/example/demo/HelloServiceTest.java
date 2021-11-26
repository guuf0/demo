package com.example.demo;


import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloServiceTest {

    @Test
    public void multiplyBy2() {
        // given
        HelloService helloService = new HelloService();

        // when
        int result = helloService.multiplyBy2(2);

        // then
        assertEquals(result, 4);
    }
}