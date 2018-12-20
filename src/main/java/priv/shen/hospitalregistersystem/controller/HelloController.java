package priv.shen.hospitalregistersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


}
