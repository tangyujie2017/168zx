package cn.tz.www.customer.controller.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tz.www.customer.view.NewsVo;
import cn.tz.www.customer.controller.service.NewsService;
import cn.tz.www.customer.entity.repository.news.NewsRepository;
import cn.tz.www.customer.entity.table.News;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.Page;
@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;

	public Page<NewsVo> loadNewsByType(Groups groups, Page<News> page) {
		
		page  = newsRepository.findEntityPageByGroups(groups, page);
		
		
		return convertNews(page);
	}

	@SuppressWarnings("unchecked")
	private Page<NewsVo> convertNews(Page<News> page) {
		Page<NewsVo> result=new Page<>(page.getPageSize(), page.getCurrentPage());
		result.setFromIndex(page.getFromIndex());
		result.setTotalCount(page.getTotalCount());
		result.setTotalPageCount(page.getTotalPageCount());
		List<NewsVo> vos = new ArrayList<NewsVo>();
		page.getItems().stream().forEach(a -> {
			News news=(News)a;
			NewsVo vo = new NewsVo();
			vo.setId(news.getId());
			vo.setTitle(news.getTitle());
			vo.setContext(news.getContext());
			vo.setViewTimes(news.getViewTimes());
			//vo.setImg(a.getImg());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			vo.setCreateTime(formatter.format(news.getCreateTime()));
			vos.add(vo);
		});
		result.setItems(vos);
		return  result;
	}

	@Transactional
	public void updateViewTimes(Long newsId) {
		News n = newsRepository.find(newsId);
		n.setViewTimes(n.getViewTimes() + 1);
		newsRepository.update(n);
	}

	@Override
	public NewsVo getNewsById(Long newsId) {
		News n = newsRepository.find(newsId);
		NewsVo vo = new NewsVo();
		vo.setId(n.getId());
		vo.setTitle(n.getTitle());
		vo.setContext(n.getContext());
		vo.setViewTimes(n.getViewTimes());
		//vo.setImg(n.getImg());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vo.setCreateTime(formatter.format(n.getCreateTime()));
		return vo;
	}
}
