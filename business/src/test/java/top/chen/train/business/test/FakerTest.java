package top.chen.train.business.test;

import cn.hutool.core.bean.BeanUtil;
import com.github.javafaker.Faker;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.chen.train.business.config.BusinessApplication;
import top.chen.train.business.domain.Student;
import top.chen.train.business.req.StudentSaveReq;
import top.chen.train.business.service.StudentService;

import java.util.Locale;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: FakerTest
 */
@SpringBootTest(classes = BusinessApplication.class)
public class FakerTest {
    @Resource
    private StudentService studentService;

    @Test
    void TestFaker(){
        Faker faker = new Faker(Locale.CHINA);

        for (int i=0; i < 100000; i++){
            String username = faker.name().username();
            Integer age = faker.number().numberBetween(18, 25);
            String phone = faker.phoneNumber().cellPhone();
            String email = faker.internet().emailAddress();
            String school = faker.university().name();

            Student student = Student.builder()
                    .username(username)
                    .age(age)
                    .phone(phone)
                    .email(email)
                    .school(school)
                    .build();
            StudentSaveReq studentSaveReq = BeanUtil.copyProperties(student, StudentSaveReq.class);
//            System.out.println(studentSaveReq);
            studentService.save(studentSaveReq);
        }
    }
}
