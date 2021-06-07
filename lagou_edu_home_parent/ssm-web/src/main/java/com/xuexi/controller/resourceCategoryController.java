package com.xuexi.controller;

import com.xuexi.domain.ResourceCategory;
import com.xuexi.domain.ResponseResult;
import com.xuexi.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resourceCategory")
public class resourceCategoryController {

    @Autowired
    private ResourceCategoryService  resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询所有分类信息成功",allResourceCategory);

    }
}
