package com.example.springdemo1.util;

import org.springframework.stereotype.Component;

@Component("fooFormatter")
public class FooFormatter implements Formatter{
    @Override
    public String format(){
        return "foo";
    }
}
