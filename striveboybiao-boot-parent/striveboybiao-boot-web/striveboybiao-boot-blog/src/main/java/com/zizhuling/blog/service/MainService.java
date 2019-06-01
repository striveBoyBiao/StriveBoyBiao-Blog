package com.zizhuling.blog.service;

import com.zizhuling.blog.utils.PageInfo;

import java.util.Map;

/**
 * 城市业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface MainService {
    /**
     * 分页查询首页,慢生活,学无止境 界面数据
     * @return
     */
    public PageInfo findLife(Map<String, Object> map);
    /**
     * 查询首页,慢生活,学无止境 详细界面数据
     * @return
     */
    public PageInfo findlifeDetails(Map<String, Object> map);
    /**
     * 分页查询忙里偷闲界面数据
     * @return
     */
    public PageInfo findDoing(Map<String, Object> map);
    /**
     * 查询标签界面数据
     * @return
     */
    public PageInfo findTags(Map<String, Object> map);
    /**
     * 分页查询相册界面数据
     * @return
     */
    public PageInfo findPhoto(Map<String, Object> map);
}
