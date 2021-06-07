package com.xuexi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuexi.dao.PromotionAdMapper;
import com.xuexi.domain.PromotionAd;
import com.xuexi.domain.PromotionAdVO;
import com.xuexi.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;
    /*
     分页查询广告信息
     */
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<PromotionAd> allPromotionAdByPage = promotionAdMapper.findAllPromotionAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allPromotionAdByPage);
        return pageInfo;
    }

    @Override
    public void updatePromotionAdStatus(int id,int status ) {

        //封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setUpdateTime(new Date());
        promotionAd.setStatus(status);
        promotionAd.setId(id);

        //调用mapper
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
