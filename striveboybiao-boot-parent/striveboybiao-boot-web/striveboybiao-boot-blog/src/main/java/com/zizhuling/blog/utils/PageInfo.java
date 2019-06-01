package com.zizhuling.blog.utils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * 
 * @author igeek
 *
 */
@Setter
@Getter
public class PageInfo<T> implements Serializable{
	/**当前页*/
	private String pageNo;
	/**每页大小*/
	public static int pageSize =10;
	/**每页开始*/
	public static int pageBegin=0;
	/**总共多少条数据*/
	private int rowCount;
	/**页的数量*/
	private int pageCount;
	/**每页数据*/
	private List<T> pageData;
	/**查询的数量*/
	private Integer queryCount;
	/**类型*/
	private String type;
	/**状态*/
	private String state;
	/**最新文章*/
	private List<T> newsData;
	/**评论排行*/
	private List<T> rankData;
	/**相关文档*/
	private List<T> relateData;
	/**推荐排行*/
	private List<T> recommendData;
	/**阅读排行*/
	private List<T> readingData;
	/**上一篇*/
	private T onData;
	/**下一篇*/
	private T underData;
	/**文章详细*/
	private T articleData;

	
	public PageInfo(String pageNo) {
		this.pageNo = pageNo;
		this.pageBegin = (Integer.parseInt(pageNo) - 1)*pageSize;
		
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		PageInfo.pageSize = pageSize;
	}

	public static int getPageBegin() {
		return pageBegin;
	}

	public static void setPageBegin(int pageBegin) {
		PageInfo.pageBegin = pageBegin;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Integer getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<T> getNewsData() {
		return newsData;
	}

	public void setNewsData(List<T> newsData) {
		this.newsData = newsData;
	}

	public List<T> getRankData() {
		return rankData;
	}

	public void setRankData(List<T> rankData) {
		this.rankData = rankData;
	}

	public List<T> getRelateData() {
		return relateData;
	}

	public void setRelateData(List<T> relateData) {
		this.relateData = relateData;
	}

	public List<T> getRecommendData() {
		return recommendData;
	}

	public void setRecommendData(List<T> recommendData) {
		this.recommendData = recommendData;
	}

	public List<T> getReadingData() {
		return readingData;
	}

	public void setReadingData(List<T> readingData) {
		this.readingData = readingData;
	}

	public T getOnData() {
		return onData;
	}

	public void setOnData(T onData) {
		this.onData = onData;
	}

	public T getUnderData() {
		return underData;
	}

	public void setUnderData(T underData) {
		this.underData = underData;
	}

	public T getArticleData() {
		return articleData;
	}

	public void setArticleData(T articleData) {
		this.articleData = articleData;
	}
}
