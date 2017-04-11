package cn.tz.www.admin.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Controller
public class MainController extends WebMvcConfigurerAdapter {
	@RequestMapping("/")
	public String index(Principal principal){
		if(principal==null){
			return "redirect:/login";
		}
		return "index";
		
	}
	@RequestMapping("/test")
	public String test(Principal principal){
		if(principal==null){
			return "redirect:/login";
		}
		return "index";
		
	}
}
