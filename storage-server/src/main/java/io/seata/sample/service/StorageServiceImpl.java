package io.seata.sample.service;

import io.seata.sample.dao.StorageDao;
import io.seata.sample.entity.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author 郭宇军
 */
@Service("storageServiceImpl")
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageDao storageDao;

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->扣减库存开始");
        Storage storage= storageDao.selectProductId(productId);
        if (ObjectUtils.isEmpty(storage)){
            throw new RuntimeException("产品：" + productId + "，不存在.");
        }

        if (storage.getResidue() - count < 0){
            throw new RuntimeException("产品：" + productId + "，库存不足.");
        }
        storageDao.decrease(productId,count);
        LOGGER.info("------->扣减库存结束");
    }
}
