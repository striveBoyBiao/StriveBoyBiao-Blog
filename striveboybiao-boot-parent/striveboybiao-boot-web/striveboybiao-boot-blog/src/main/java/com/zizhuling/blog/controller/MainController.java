package com.zizhuling.blog.controller;


import com.zizhuling.blog.service.MainService;
import com.zizhuling.blog.utils.Constants;
import com.zizhuling.blog.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by 11697 on 2018/12/23.
 */
@Controller
public class MainController {
    private static final Logger LOGGER= LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MainService mainService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 跳到首页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "blog/index";
    }
    /**
     * 跳到首页
     * @return
     */
    @RequestMapping("/index")
    public String main() {
        return "blog/index";
    }

    /**
     * 跳到关于我
     * @return
     */
    @RequestMapping("/about")
    public String about() {
        return "blog/about";
    }

    /**
     * 跳到慢生活
     * @return
     */
    @RequestMapping("/life")
    public String life() {
        return "blog/life";
    }

    /**
     * 跳到忙里偷闲
     * @return
     */
    @RequestMapping("/doing")
    public String doing() {
        return "blog/doing";
    }

    /**
     * 跳到学无止境
     * @return
     */
    @RequestMapping("/learn")
    public String learn() {
        return "blog/learn";
    }


    /**
     * 跳到相册
     * @return
     */
    @RequestMapping("/photo")
    public String photo() {
        return "blog/photo";
    }

    /**
     * 跳到留言板
     * @return
     */
    @RequestMapping("/saying")
    public String saying() {
        return "blog/saying";
    }

    /**
     * 跳到详情页
     * @return
     */
    @RequestMapping("/article")
    public String article(HttpServletRequest request) {
        HttpSession session=request.getSession();
        String cid=request.getParameter("cid");
        String type=request.getParameter("type");
        if(StringUtils.isNotBlank(cid)){
            session.setAttribute("cid",cid);
            session.setAttribute("type",type);
        }
        return "blog/article";
    }

    /**
     * 跳到标签云
     * @return
     */
    @RequestMapping("/tags")
    public String tags() {
        return "blog/tags";
    }

    /**
     * 跳到音乐
     * @return
     */
    @RequestMapping("/music")
    public String music() {
        return "blog/music";
    }

    /**
     * 跳到标签云
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findTags")
    public PageInfo findTags(HttpServletRequest request, Model model) {
        PageInfo pageInfo=null;
        String key= Constants.STRING_ONE+Constants.TAGS;
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*缓存存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
             pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("type",Constants.STRING_ONE);
            pageInfo=mainService.findTags(map);
            /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        model.addAttribute("newsdata",pageInfo.getNewsData());
        model.addAttribute("rankdata",pageInfo.getRankData());
        model.addAttribute("recommenddata",pageInfo.getRecommendData());
        return pageInfo;
    }

    /**
     * 跳到音乐
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findMusic")
    public PageInfo findMusic(HttpServletRequest request, Model model) {
        PageInfo pageInfo=null;
        String key= Constants.STRING_ONE+Constants.MUSIC;
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*缓存存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("type",Constants.STRING_ONE);
            pageInfo=mainService.findTags(map);
            /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        return pageInfo;
    }




    /**
     * 分页查询首页数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findIndex")
    public PageInfo findIndex(HttpServletRequest request){
        PageInfo pageInfo=null;
        String key= Constants.STRING_ONE+Constants.INDEX+request.getParameter("pageNo");
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*缓存存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("type",Constants.STRING_ONE);
            map.put("pageNo",request.getParameter("pageNo"));
            map.put("news",0);
            map.put("newslength",6);
            map.put("rank",0);
            map.put("ranklength",6);
            pageInfo=mainService.findLife(map);
            /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        return pageInfo;
    }

    /**
     * 查询首页详细界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findIndexDetails")
    public PageInfo findIndexDetails(HttpServletRequest request, HttpServletResponse response, Model model){
        PageInfo pageInfo=null;
        Object cid;
        Object type=request.getSession().getAttribute("type");
        String id=request.getParameter("cid");
        if(StringUtils.isNotBlank(id)){
            cid=id;
        }else{
            cid=request.getSession().getAttribute("cid");
        }
        if(cid==null||type==null){
            try {
                response.sendRedirect("index");
            } catch (IOException e) {
                LOGGER.info("文章访问重定向错误");
            }
        }
        String key= Constants.STRING_ONE+Constants.INDEXDETAILS+cid;
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*缓存存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("cid",cid);
            map.put("type",type);
            pageInfo=mainService.findlifeDetails(map);
            /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        return pageInfo;
    }


    /**
     * 分页查询慢生活界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findLife")
    public PageInfo findLife(HttpServletRequest request){
        PageInfo pageInfo=null;
        String key= Constants.STRING_THREE+Constants.LIFE+request.getParameter("pageNo")+request.getParameter("lmlb");
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*缓存存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("type",Constants.STRING_THREE);
            map.put("lmlb",request.getParameter("lmlb"));
            map.put("pageNo",request.getParameter("pageNo"));
            map.put("news",0);
            map.put("newslength",6);
            map.put("rank",0);
            map.put("ranklength",6);
            pageInfo=mainService.findLife(map);
            /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        return pageInfo;
    }

    /**
     * 分页查询忙里偷闲界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findDoing")
    public PageInfo findDoing(HttpServletRequest request){
        PageInfo pageInfo=null;
        String key= Constants.STRING_FOUR+Constants.DOING+request.getParameter("pageNo");
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*判断缓存是否存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("pageNo",request.getParameter("pageNo"));
            pageInfo=mainService.findDoing(map);
             /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        return pageInfo;
    }


    /**
     * 分页查询学无止境界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findLearn")
    public PageInfo findLearn(HttpServletRequest request){
        PageInfo pageInfo=null;
        String key= Constants.STRING_THREE+Constants.LEARN+request.getParameter("pageNo")+request.getParameter("lmlb")+request.getParameter("search");
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*缓存存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("type",Constants.STRING_FIVE);
            map.put("lmlb",request.getParameter("lmlb"));
            map.put("pageNo",request.getParameter("pageNo"));
            map.put("search",request.getParameter("search"));
            map.put("news",0);
            map.put("newslength",8);
            map.put("rank",0);
            map.put("ranklength",8);
            pageInfo=mainService.findLife(map);
             /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        return pageInfo;
    }


    /**
     * 分页查询相册界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findPhoto")
    public PageInfo findPhoto(HttpServletRequest request){
        PageInfo pageInfo=null;
        String key= Constants.STRING_SEX+Constants.PHOTO+request.getParameter("pageNo");
        ValueOperations<String,PageInfo> operations= redisTemplate.opsForValue();
        /*缓存存在*/
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            pageInfo=operations.get(key);
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("pageNo",request.getParameter("pageNo"));
            pageInfo=mainService.findPhoto(map);
            /*插入缓存*/
            operations.set(key, pageInfo, 120, TimeUnit.SECONDS);
        }
        return pageInfo;
    }


}
