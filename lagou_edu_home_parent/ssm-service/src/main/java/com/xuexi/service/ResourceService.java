package com.xuexi.service;

import com.github.pagehelper.PageInfo;
import com.xuexi.domain.Resource;
import com.xuexi.domain.ResourceVo;

import java.util.List;

public interface ResourceService {

    /*
     资源分页&多条件查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
