package cn.tz.www.customer.controller.service;

import java.util.List;

import cn.tz.www.customer.view.NewsVo;

public interface NewsService {
	public List<NewsVo> loadNewsByType(Integer type) ;
	public void updateViewTimes(Long newsId);
	public NewsVo getNewsById(Long newsId);
}
