package top.chen.train.common.Enums;

public class BookTypeEnumTest {
    public static void main(String[] args) {
        System.out.println(BookTypeEnum.COMPUTE_TYPE + " " + BookTypeEnum.COMPUTE_TYPE.getCode() + " " + BookTypeEnum.COMPUTE_TYPE.getType());
        System.out.println(BookTypeEnum.LITERATURE_TYPE);
        System.out.println(BookTypeEnum.HISTORY_TYPE);
    }
}