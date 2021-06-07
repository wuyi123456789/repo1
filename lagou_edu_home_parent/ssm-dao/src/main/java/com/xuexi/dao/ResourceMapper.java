package com.xuexi.dao;

import com.xuexi.domain.Resource;
import com.xuexi.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    /*
     资源分页&多条件查询
     */
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);

}
