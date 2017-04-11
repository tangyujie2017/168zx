package cn.tz.www.admin.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

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
	@RequestMapping("/roleManage")
	public String roleManage(Principal principal,HttpServletResponse response){
		if(principal==null){
			return "redirect:/login";
		}
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		return "role_manage";
		
	}
	@RequestMapping("/releaseInfos")
	public String releaseInfos(Principal principal,HttpServletResponse response){
		if(principal==null){
			return "redirect:/login";
		}
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		return "release_infos";
		
	}
	@RequestMapping("/releaseNews")
	public String releaseNews(Principal principal,HttpServletResponse response){
		if(principal==null){
			return "redirect:/login";
		}
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		return "release_news";
		
	}
	@RequestMapping("/releaseSlide")
	public String releaseSlide(Principal principal,HttpServletResponse response){
		if(principal==null){
			return "redirect:/login";
		}
	  System.out.println(response.getHeader("X-Frame-Options"));
	  
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		return "release_slide";
		
		
	}
	@RequestMapping("/usersManage")
	public String usersManage(Principal principal){
		if(principal==null){
			return "redirect:/login";
		}
		return "users_manage";
		
	}
	
}
