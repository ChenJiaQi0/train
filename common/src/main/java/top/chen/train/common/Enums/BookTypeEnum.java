package top.chen.train.common.Enums;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: BookTypeEnum
 */
public enum BookTypeEnum {
    COMPUTE_TYPE(1, "计算机类"),
    LITERATURE_TYPE(2, "文学类"),
    HISTORY_TYPE(3, "历史类");

    private Integer code;
    private String type;

    BookTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    BookTypeEnum() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BookTypeEnum{" +
                "code=" + code +
                ", type='" + type + '\'' +
                '}';
    }
}
