package cn.tz.www.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tz.www.customer.controller.service.NewsService;
import cn.tz.www.customer.entity.tools.JsonObj;

@Controller
public class CustomerInfoController {
	@Autowired
	private NewsService newsService;

	// 注册
	@RequestMapping(value = "/api/customer/info/list")
	@ResponseBody
	public JsonObj getNewsByType( Integer type) {
		
		if (type == null) {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}

		return JsonObj.newSuccessJsonObj("获取消息成功", newsService.loadNewsByType(type));

	}

	@RequestMapping(value = "/api/customer/info/updateViews")
	@ResponseBody
	public JsonObj updateViews(Long newsId) {
		if (newsId == null) {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}
		newsService.updateViewTimes(newsId);
		return JsonObj.newSuccessJsonObj("更新浏览次数成功");

	}

	@RequestMapping(value = "/api/customer/info/getByid")
	@ResponseBody
	public JsonObj getNewsById(Long newsId) {
		if (newsId == null) {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}

		return JsonObj.newSuccessJsonObj("更新浏览次数成功", newsService.getNewsById(newsId));

	}
}
