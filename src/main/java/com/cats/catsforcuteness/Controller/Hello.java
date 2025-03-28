package com.cats.catsforcuteness.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cFc/hello")
public class Hello {

    @GetMapping
    public String sayHello(){
        return "Hello, Cats for Cuteness from java!";
    }
}
