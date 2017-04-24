package cn.tz.www.admin.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tz.www.admin.controller.service.NewsService;
import cn.tz.www.admin.controller.service.UserService;
import cn.tz.www.admin.controller.util.PageParam;
import cn.tz.www.admin.controller.util.PageVo;
import cn.tz.www.customer.entity.table.News;
import cn.tz.www.customer.entity.table.Slide;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.JsonObj;
import cn.tz.www.customer.entity.tools.Page;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private UserService userService;

	@PostMapping("/news/create")
	@ResponseBody
	public JsonObj createNews(News news, Principal principal) {
		//
		if (principal == null) {
			return JsonObj.newErrorJsonObj("用户已过期请从新登录");
		}
		news.setCreateTime(new Date());
		news.setStatus(1);
		news.setViewTimes(createViewTimes());
		newsService.createNews(news);
		// 设置USer
		if (!principal.getName().equals("admin")) {
			news.setCreateUser(userService.loadUserByLogin(principal.getName()));
		}

		return JsonObj.newSuccessJsonObj("创建消息成功");
	}

	@PostMapping("/news/delNews")
	@ResponseBody
	public JsonObj delNews(Long id, Principal principal) {
		//
		if (principal == null) {
			return JsonObj.newErrorJsonObj("用户已过期请从新登录");
		}

		newsService.delNews(id);
		// 设置USer

		return JsonObj.newSuccessJsonObj("删除消息成功");
	}

	private Integer createViewTimes() {
		int max = 2000;
		int min = 200;
		Random random = new Random();

		int s = random.nextInt(max) % (max - min + 1) + min;

		return s;
	}

	@RequestMapping("/news/list")
	@ResponseBody
	public PageVo<News> listNews(@Valid PageParam param, BindingResult result, Principal principal) {
		//
		if (principal == null) {
			JsonObj.newErrorJsonObj("用户已过期请从新登录");
		}
		int pageSize = param.getPageSize();
		int currentPage = param.getPageIndex();
		Groups g = new Groups();
		g.Add("status", 1);
		g.setOrderby("createTime");
		Page<News> page = new Page<News>(pageSize, currentPage);
		newsService.newsList(g, page);
		PageVo<News> vo = new PageVo<>();
		// String picBasePath = values.getUploadedImagesUrlPrefix();
		vo.setTotal(page.getTotalCount());
		// vo.setPicPath(picBasePath);
		vo.setItems(page.getItems());
		return vo;
	}

	@RequestMapping("/news/loadNewsById")
	@ResponseBody
	public JsonObj loadNewsById(Long newsId, Principal principal) {
		//
		if (principal == null) {
			JsonObj.newErrorJsonObj("用户已过期请从新登录");
		}

		return JsonObj.newSuccessJsonObj("创建消息成功", newsService.loadNewsById(newsId));
	}
}