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
@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;

	public List<NewsVo> loadNewsByType(Integer type) {
		Groups g = new Groups();
		g.Add("type", type);
		g.Add("status", 1);
		g.setOrderby("createTime ");
		List<News> list = newsRepository.findEntityByGroups(g);
		return convertNews(list);
	}

	private List<NewsVo> convertNews(List<News> list) {
		List<NewsVo> vos = new ArrayList<NewsVo>();
		list.stream().forEach(a -> {
			NewsVo vo = new NewsVo();
			vo.setId(a.getId());
			vo.setTitle(a.getTitle());
			vo.setContext(a.getContext());
			vo.setViewTimes(a.getViewTimes());
			vo.setImg(a.getImg());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			vo.setCreateTime(formatter.format(a.getCreateTime()));
			vos.add(vo);
		});
		return vos;
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
		vo.setImg(n.getImg());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vo.setCreateTime(formatter.format(n.getCreateTime()));
		return vo;
	}
}
