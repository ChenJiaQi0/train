package top.chen.train.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.chen.train.common.resp.CommonResp;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/11/24
 * @description: BusinessFeign
 */
//@FeignClient("business")
//@FeignClient(name = "business", url = "http://127.0.0.1:8002/business")
@FeignClient(name = "business", path = "/business")
public interface BusinessFeign {
    @GetMapping("/hello")
    String hello();

    @GetMapping("/admin/daily-train/gen-daily/{date}")
    CommonResp<Object> genDaily(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
}
