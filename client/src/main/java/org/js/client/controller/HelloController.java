package org.js.client.controller;

import org.js.client.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiaShun
 * @date 2018/8/10 22:20
 */
@RestController
public class HelloController {
    @Autowired
    private IHelloService helloService;

    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name){
        return helloService.sayHello(name);
    }
}
