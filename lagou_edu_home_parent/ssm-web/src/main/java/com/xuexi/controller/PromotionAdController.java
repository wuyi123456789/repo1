package com.xuexi.controller;

import com.github.pagehelper.PageInfo;
import com.xuexi.domain.PromotionAd;
import com.xuexi.domain.PromotionAdVO;
import com.xuexi.domain.ResponseResult;
import com.xuexi.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
     广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){

        PageInfo<PromotionAd> allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        return new ResponseResult(true,200,"广告分页查询成功",allPromotionAdByPage);
    }

    /*
      广告动态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){
        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true,200,"广告动态上下线成功",null);
    }
}
