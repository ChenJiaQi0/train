package top.chen.train;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: TestController
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
