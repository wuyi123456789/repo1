package com.xuexi.dao;

import com.xuexi.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /*
     查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();
}
