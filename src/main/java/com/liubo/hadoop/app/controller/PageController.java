package com.liubo.hadoop.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "file_table_list";
    }

    @RequestMapping("{page}")
    public String forwardPage(@PathVariable("page") String page){
        return page;
    }

}
