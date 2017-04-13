package cn.tz.www.admin.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tz.www.admin.controller.service.NewsService;
import cn.tz.www.admin.controller.service.UserService;
import cn.tz.www.customer.entity.table.News;
import cn.tz.www.customer.entity.tools.JsonObj;

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

	private Integer createViewTimes() {
		int max = 2000;
		int min = 200;
		Random random = new Random();

		int s = random.nextInt(max) % (max - min + 1) + min;

		return s;
	}
}
