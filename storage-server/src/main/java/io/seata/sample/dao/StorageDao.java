package io.seata.sample.dao;

import io.seata.sample.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 郭宇军
 */
@Repository
public interface StorageDao {

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

    @Select("SELECT * from storage where product_id = #{value}")
    Storage selectProductId(Long productId);
}
