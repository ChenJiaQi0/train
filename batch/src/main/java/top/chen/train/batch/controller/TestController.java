package top.chen.train.batch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenQi
 * @date 2023/11/23
 * @description: TestController
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World! Batch!";
    }
}
