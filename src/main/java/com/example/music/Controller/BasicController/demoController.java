package com.example.music.Controller.BasicController;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    @GetMapping("/demo")
    public void demo(){
        System.out.println(111);
        return;
    }

    @GetMapping("/menu/demo1")
    public void demo2(){
        System.out.println(222);
        return;
    }

    @GetMapping("/User/demo2")
    public void getMapping(){
        System.out.println(333);
        return;
    }

} 
