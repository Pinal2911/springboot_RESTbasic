package net.javaguidelines.springboot_RESTbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    //@GetMapping- for get request (HTTP GET REQUEST)
    //http://localhost:8080/hello-world

    @GetMapping("/hello-world")
    public String hellowWorld(){
        return "hello world!";
    }
}
