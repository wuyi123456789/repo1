package com.xuexi.service.impl;

import com.xuexi.dao.PromotionSpaceMapper;
import com.xuexi.domain.PromotionSpace;
import com.xuexi.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return promotionSpaceMapper.findAllPromotionSpace();
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //封装数据
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setIsDel(0);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    /*
     根据id查询广告位信息
     */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        return promotionSpaceMapper.findPromotionSpaceById(id);
    }

    /*
     修改广告位名称
    */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        //封装数据
        promotionSpace.setUpdateTime(new Date());
        //调用mapper
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
