package io.seata.sample.feign;

import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 郭宇军
 */
@FeignClient(value = "account-server")
@Component
public interface AccountApi {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     * @return
     */
    @RequestMapping("/account/decrease")
    String decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
