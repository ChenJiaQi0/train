package top.chen.train.batch.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.batch.feign.BusinessFeign;

/**
 * @author ChenQi
 * @date 2023/11/23
 * @description: TestController
 */
@RestController
@Slf4j
public class TestController {
    @Resource
    private BusinessFeign businessFeign;

    @GetMapping("/hello")
    public String hello(){
        String businessFeignHello = businessFeign.hello();
        log.info("business hello：{}", businessFeignHello);
        return "Hello World! Batch!";
    }

}
