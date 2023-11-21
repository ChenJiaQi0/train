package top.chen.train.generator.gen;

import freemarker.template.TemplateException;
import top.chen.train.generator.util.FreemarkerUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: ServerGenerator
 */
public class ServerGenerator {
    static String toPath = "generator/src/main/java/top/chen/train/generator/test/";

    static {
        new File(toPath).mkdirs();
    }

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        Map<String, Object> param = new HashMap<>();
        param.put("domain", "Test1");
        FreemarkerUtil.generator(toPath + "Test1.java", param);
    }
}
