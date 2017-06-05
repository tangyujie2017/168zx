package cn.tz.www.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tz.www.customer.controller.service.ProductService;
import cn.tz.www.customer.entity.table.Product;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.JsonObj;
import cn.tz.www.customer.entity.tools.Page;
import cn.tz.www.customer.util.PageParamProduct;

@Controller
public class CustomerProductController {
	@Autowired
	private ProductService productService;
	@Value("${customer.images.url-prefix}")
	private String imgUrl;
	// 注册
	@PostMapping(value = "/api/customer/product/list")
	@ResponseBody
	public JsonObj loadProductList(@Valid PageParamProduct param) {
		
		if (param != null) {
			int pageSize = param.getPageSize();
			int currentPage = param.getPageIndex();
			Groups g = new Groups();
			
			Page<Product> page = new Page<Product>(pageSize, currentPage);
			return JsonObj.newSuccessJsonObj("获取消息成功", productService.loadProductList(g, page, imgUrl));
			
		}else{
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}
		

	}

	

	@GetMapping(value = "/api/customer/product/loadProductById")
	@ResponseBody
	public JsonObj getProductById(Long productId) {
		if (productId == null) {
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}

		return JsonObj.newSuccessJsonObj("获取数据成功", productService.getProductById(productId, imgUrl));

	}
}
