package com.example.springdemo1.service;

import com.example.springdemo1.util.FooFormatter;
import com.example.springdemo1.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    private Formatter formatter;

    @Autowired
    public FooService(@Qualifier("barFormatter") Formatter formatter){
        this.formatter = formatter;
    }

//    @Autowired
//    public void setFooFormatter(FooFormatter fooFormatter){
//        this.fooFormatter = fooFormatter
//    }

    public String fetchFoo(){
        return formatter.format();
    }
}
