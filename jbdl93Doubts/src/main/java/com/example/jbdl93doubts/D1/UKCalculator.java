package com.example.jbdl93doubts.D1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("uk-bean")
public class UKCalculator implements Calculator {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }
}
