package top.chen.train.generator.gen;

import cn.hutool.core.util.StrUtil;
import top.chen.train.member.enums.PassengerTypeEnum;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: EnumGenerator 枚举代码生成器
 */
public class EnumGenerator {
    static String path = "web/src/assets/js/enums.js";

    public static void main(String[] args) {
        StringBuffer bufferObject = new StringBuffer();
        StringBuffer bufferArray = new StringBuffer();
        long begin = System.currentTimeMillis();
        try {
            toJson(PassengerTypeEnum.class, bufferObject, bufferArray);
            StringBuffer buffer = bufferObject.append("\r\n").append(bufferArray);
            writeJs(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行耗时:" + (end - begin) + " 毫秒");
    }

    private static void toJson(Class clazz, StringBuffer bufferObject, StringBuffer bufferArray) throws Exception {
        // enumConst：将YesNoEnum变成YES_NO
        String enumConst = StrUtil.toUnderlineCase(clazz.getSimpleName())
                .toUpperCase().replace("_ENUM", "");
        Object[] objects = clazz.getEnumConstants();
        Method name = clazz.getMethod("name");
        Method getDesc = clazz.getMethod("getDesc");
        Method getCode = clazz.getMethod("getCode");

        // 生成对象
        bufferObject.append(enumConst).append("={");
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            bufferObject.append(name.invoke(obj)).append(":{code:\"").append(getCode.invoke(obj)).append("\", desc:\"").append(getDesc.invoke(obj)).append("\"}");
            if (i < objects.length - 1) {
                bufferObject.append(",");
            }
        }
        bufferObject.append("};\r\n");

        // 生成数组
        bufferArray.append(enumConst).append("_ARRAY=[");
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            bufferArray.append("{code:\"").append(getCode.invoke(obj)).append("\", desc:\"").append(getDesc.invoke(obj)).append("\"}");
            if (i < objects.length - 1) {
                bufferArray.append(",");
            }
        }
        bufferArray.append("];\r\n");
    }

    /**
     * 写文件
     *
     * @param stringBuffer 文件内容
     */
    public static void writeJs(StringBuffer stringBuffer) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            System.out.println(path);
            osw.write(stringBuffer.toString());
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
