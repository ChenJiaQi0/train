package top.chen.train.member.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: MemberApplication
 */
@SpringBootApplication
@ComponentScan("top.chen")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
