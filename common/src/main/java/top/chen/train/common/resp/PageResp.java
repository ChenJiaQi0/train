package top.chen.train.common.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: PageResp
 */
@Data
public class PageResp<T> implements Serializable {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 当前页的列表
     */
    private List<T> list;
}
