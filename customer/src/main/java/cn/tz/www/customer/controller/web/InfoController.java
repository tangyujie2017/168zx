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
public class InfoController {

	@Autowired
	private SliderService sliderService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ProductService productService;
	@Value("${customer.images.url-prefix}")
	private String imgUrl;

	/**
	 * 
	 * 证券要闻列表
	 */
	@RequestMapping("/safe/zqyw/list")
	public String zqyw() {

		return "/newlist";
	}

	/**
	 * 
	 * a股分析
	 */
	@RequestMapping("/safe/agfx/list")
	public String agfx() {

		return "/analysis";
	}

	/**
	 * 
	 * 宏观策略
	 */
	@RequestMapping("/safe/hgcl/list")
	public String hgcl() {

		return "/strategy";
	}

	/**
	 * 
	 * 产品理财
	 */
	@RequestMapping("/safe/cplc/list")
	public String cplc() {

		return "/product";
	}
	
	//拿取数据

	@RequestMapping(value = "/safe/info/data")
	@ResponseBody

	public JsonObj getNewsByType(Integer type, @RequestParam(value="pageSize" ,defaultValue="10")Integer pageSize, @RequestParam(value="currentPage" ,defaultValue="1")Integer currentPage) {

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

	@GetMapping(value = "/safe/info/product/list")
	@ResponseBody
	public JsonObj loadProductList(@RequestParam(value="pageSize" ,defaultValue="10")Integer pageSize, @RequestParam(value="currentPage" ,defaultValue="1")Integer currentPage) {
		if (pageSize != null && currentPage != null) {
			Groups g = new Groups();

			Page<Product> page = new Page<Product>(pageSize, currentPage);
			return JsonObj.newSuccessJsonObj("获取消息成功", productService.loadProductList(g, page, imgUrl));

		} else {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}

	}
}
