package io.seata.sample.service;

import io.seata.sample.dao.AccountDao;
import io.seata.sample.entity.Account;
import io.seata.sample.feign.OrderApi;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author 郭宇军
 */
@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private OrderApi orderApi;

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->扣减账户开始account中");
        Account account = accountDao.selectOne(userId);
        if (ObjectUtils.isEmpty(account)){
            throw new RuntimeException("账户："+userId+"不存在");
        }

        BigDecimal residue = account.getResidue();
        if (residue.subtract(money).compareTo(money) < 0) {
            throw new RuntimeException("账户：" + userId + "，余额不足.");
        }

        accountDao.decrease(userId,money);
        LOGGER.info("------->扣减账户结束account中");

        //修改订单状态，此调用会导致调用成环
        LOGGER.info("修改订单状态开始");
        String mes = orderApi.update(userId, money.multiply(new BigDecimal("0.00")),0);
        LOGGER.info("修改订单状态结束：{}",mes);
        //throw new RuntimeException("账户操作异常！");
    }
}
