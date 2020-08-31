package io.seata.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 郭宇军
 */
@FeignClient(value = "storage-server")
@Component
public interface StorageApi {

    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    @GetMapping(value = "/storage/decrease")
    String decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
