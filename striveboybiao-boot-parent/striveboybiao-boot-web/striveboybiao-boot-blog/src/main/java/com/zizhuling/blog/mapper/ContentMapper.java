package com.zizhuling.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Mapper
public interface ContentMapper {
    /**查询忙里偷闲*/
    List<Map<String,Object>> findDoing(Map<String, Object> map);
    int queryCount(Map<String, Object> map);

    /**查询慢生活数据*/
    List<Map<String,Object>> findLife(Map<String, Object> map);
    Map<String,Object> findlifeDetails(Map<String, Object> map);


    /**查询数据数量*/
    int queryLifeCount(Map<String, Object> map);
    /**查询最新文章*/
    List<Map<String,Object>> findLifeNewsData(Map<String, Object> map);
    /**查询评论排行*/
    List<Map<String,Object>> findLifeRankData(Map<String, Object> map);
    /**查询相关文章*/
    List<Map<String,Object>> findLifeRelateData(Map<String, Object> map);
    /**查询站长荐文章*/
    List<Map<String,Object>> findRecommendData(Map<String, Object> map);
    /**查询图文推荐文章*/
    List<Map<String,Object>> findReadingData(Map<String, Object> map);


    /**查询上一篇文章*/
    Map<String,Object> findLifeOnData(Map<String, Object> map);
    /**查询下一篇文章*/
    Map<String,Object> findLifeUnderData(Map<String, Object> map);
    /**修改阅读量*/
    void updateClickRate(Map<String, Object> map);
    /**查询图片*/
    List<Map<String,Object>> queryFiles(Map<String, Object> map);
    /**查询图片数量*/
    int queryFilesCount(Map<String, Object> map);
}
