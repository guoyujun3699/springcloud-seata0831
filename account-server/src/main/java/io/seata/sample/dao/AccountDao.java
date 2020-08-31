package io.seata.sample.dao;

import java.math.BigDecimal;

import io.seata.sample.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author 郭宇军
 */
public interface AccountDao {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);

    @Select("SELECT * FROM account where user_id = #{value}")
    Account selectOne(Long userId);
}
