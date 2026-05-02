package com.example.jbdl93doubts.D1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("us-bean")
public class USCalculator implements Calculator {
    @Override
    public int add(int a, int b) {
        return a+b+1;
    }

    @Override
    public int sub(int a, int b) {
        return Math.abs(a-b);
    }
}
