package cn.tz.www.customer.controller.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tz.www.customer.controller.service.NewsService;
import cn.tz.www.customer.controller.service.ProductService;
import cn.tz.www.customer.controller.service.SliderService;
import cn.tz.www.customer.entity.table.News;
import cn.tz.www.customer.entity.table.Product;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.JsonObj;
import cn.tz.www.customer.entity.tools.Page;

@Controller
public class IndexController {
	@Autowired
	private SliderService sliderService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ProductService productService;
	@Value("${customer.images.url-prefix}")
	private String imgUrl;

	@RequestMapping("/web/index")
	public String index() {

		return "/index";
	}

	// 获取滚动图片
	@RequestMapping(value = "/web/slider/list")
	@ResponseBody
	public JsonObj getSliderByType(@RequestParam("type")Integer type) {
		if (type == null) {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}
		return JsonObj.newSuccessJsonObj("获取轮播图成功", sliderService.loadSliderByType(type, imgUrl));

	}

	// 加载热点及列表
	@RequestMapping(value = "/web/info/list")
	@ResponseBody
	public JsonObj getNewsByType(Integer type, Integer pageSize, Integer currentPage) {

		if (type != null && pageSize != null && currentPage != null) {

			Groups g = new Groups();
			g.Add("type", type);
			g.Add("status", 1);
			g.setOrderby("createTime");
			Page<News> page = new Page<News>(pageSize, currentPage);
			return JsonObj.newSuccessJsonObj("获取消息成功", newsService.loadNewsByType(g, page, imgUrl));

		} else {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}

	}

	// 跟新浏览量
	@RequestMapping(value = "/web/info/updateViews")
	@ResponseBody
	public JsonObj updateViews(Long newsId) {
		if (newsId == null) {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}
		newsService.updateViewTimes(newsId);
		return JsonObj.newSuccessJsonObj("更新浏览次数成功");

	}
  
	@RequestMapping(value = "/web/info/getByid")
	@ResponseBody
	public JsonObj getNewsById(Long newsId) {
		if (newsId == null) {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}

		return JsonObj.newSuccessJsonObj("更新浏览次数成功", newsService.getNewsById(newsId, imgUrl));

	}
	@GetMapping(value = "/web/product/list")
	@ResponseBody
	public JsonObj loadProductList( Integer pageSize,Integer currentPage) {
		if (pageSize!=null&&currentPage!=null) {
           Groups g = new Groups();
			
			Page<Product> page = new Page<Product>(pageSize, currentPage);
			return JsonObj.newSuccessJsonObj("获取消息成功", productService.loadProductList(g, page, imgUrl));
			
		}else{
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}
	
		
		

	}

}
