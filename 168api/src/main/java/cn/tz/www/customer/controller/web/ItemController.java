package cn.tz.www.customer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.tz.www.customer.controller.service.NewsService;
import cn.tz.www.customer.controller.service.ProductService;
import cn.tz.www.customer.view.NewsVo;
import cn.tz.www.customer.view.ProductVo;

@Controller
public class ItemController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private ProductService productService;
	@Value("${customer.images.url-prefix}")
	private String imgUrl;

	// 拿取数据

	@RequestMapping("/item/page")
	public String page(Integer type, Long id, ModelMap model) {

		if (type != null) {

			NewsVo vo = newsService.getNewsById(id, imgUrl);
			model.addAttribute("data", vo);
			if (type.equals(1)) {
				// 挣钱要闻
				return "/news";
			} else if (type.equals(2)) {
				// 挣钱要闻
				return "/analysisD";
			}else if (type.equals(3)) {
				// 挣钱要闻
				return "/operateD";
			} else if (type.equals(4)) {
				// 挣钱要闻
				return "/strategyD";
			} else {
				return "/index";
			}

		} else {
			return "/index";
		}

	}

	@RequestMapping("/item/product")
	public String product(Long id, ModelMap model) {
		if (id != null) {

			ProductVo vo = productService.getProductById(id, imgUrl);

			model.addAttribute("data", vo);

			return "/probasin";
		} else {
			return "/index";
		}

	}

}
