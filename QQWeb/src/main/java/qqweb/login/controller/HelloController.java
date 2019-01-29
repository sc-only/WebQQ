package qqweb.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @RequestMapping(value = "/index_1")
    public String index_1(){
        return "/index_1";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "/index";
    }

    @RequestMapping(value = "/index1_1")
    public String index1_1(){
        return "/index1_1";
    }

    @RequestMapping(value = "/index2")
    public String index2(){
        return "/index2";
    }

    @RequestMapping(value = "/index_2")
    public String index_2(){
        return "/index_2";
    }

    @RequestMapping(value = "/index_3")
    public String index_3(){
        return "/index_3";
    }
}
