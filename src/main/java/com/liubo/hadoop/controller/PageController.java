package com.liubo.hadoop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

    @RequestMapping("{page}")
    public String liubo(@PathVariable("page") String page){
        return page;
    }

}
