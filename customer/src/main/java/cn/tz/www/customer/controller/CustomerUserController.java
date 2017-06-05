package cn.tz.www.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tz.www.customer.controller.service.CustomerUserService;
import cn.tz.www.customer.entity.tools.JsonObj;
import cn.tz.www.customer.view.CustomerVo;

@Controller
public class CustomerUserController {
	@Autowired
	private CustomerUserService customerUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	// 免状态登录
	@GetMapping(value = "/api/customer/login")
	@ResponseBody
	public JsonObj loginCustomer(String login, String password, HttpServletRequest request,
			HttpServletResponse response) {
		if(login!=null&&!"".equals(login)&&password!=null&&!"".equals(password)){
			 JsonObj resp = customerUserService.readByMobile(login,password, passwordEncoder);
			 return resp;
		}else{
			return JsonObj.newErrorJsonObj("请填写用户名和密码");
		}
		
	   
		

		

	}

	@RequestMapping(value = "/api/customer/loadById")
	@ResponseBody
	public JsonObj loadById(Long customerId) throws Exception {
		if(customerId!=null){
			return customerUserService.LoadCustomerById(customerId);
		}else{
			return JsonObj.newErrorJsonObj("请求参数不正确");
		}
		
	}

	
}
