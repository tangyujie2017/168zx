package cn.tz.www.admin.controller.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tz.www.admin.controller.service.NewsService;
import cn.tz.www.customer.entity.repository.news.NewsRepository;
import cn.tz.www.customer.entity.table.News;

@Service
public class NewServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;

	@Transactional
	public void createNews(News news) {
		newsRepository.persist(news);
	}

}
