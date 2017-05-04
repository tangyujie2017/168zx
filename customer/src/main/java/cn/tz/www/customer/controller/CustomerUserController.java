package cn.tz.www.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
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
	// @Autowired
	// private AuthenticationManager authenticationManager;

	// @Autowired
	// private AbstractRememberMeServices rememberMeServices;

	// 检查用户名是否存在
	@RequestMapping(value = "/api/customer/check")
	@ResponseBody
	public JsonObj checkCustomer(String login) {

		return JsonObj.newSuccessJsonObj("检测成功", customerUserService.checkCustomer(login));
	}

	// 注册
	@RequestMapping(value = "/api/customer/regist")
	@ResponseBody
	public JsonObj registCustomer(String login, String password) {
		CustomerVo vo = new CustomerVo();

		vo.setLogin(login);
		vo.setPassword(password);
		vo.setUserName(login);
		return JsonObj.newSuccessJsonObj("注册成功", customerUserService.registCustomer(vo));

	}

	// 免状态登录
	@PostMapping(value = "/api/customer/login")
	@ResponseBody
	public JsonObj loginCustomer(String login, String password, HttpServletRequest request,
			HttpServletResponse response) {
		// HttpSession session = request.getSession();
		// if (session != null) {
		// session.invalidate();
		// }
		// UsernamePasswordAuthenticationToken authRequest = new
		// UsernamePasswordAuthenticationToken(login, password);
		// // Authenticate the user
		// try {
		// Authentication authentication =
		// authenticationManager.authenticate(authRequest);
		// SecurityContext securityContext = SecurityContextHolder.getContext();
		// securityContext.setAuthentication(authentication);
		// // Create a new session and add the security context.
		// session = request.getSession(true);
		// session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		// rememberMeServices.loginSuccess(request, response, authentication);
		// } catch (Exception e) {
		// return JsonObj.newErrorJsonObj("服务器报错");
		// }

		JsonObj resp = customerUserService.readByMobile(login, password);
		if (resp.isSuccess()) {
			return resp;
		}

		return JsonObj.newErrorJsonObj("用户名或密码不存在");

	}

	@RequestMapping(value = "/api/customer/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// authentication.setAuthenticated(false);
		// try {
		// rememberMeServices.logout(request, response, authentication);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// HttpSession session = request.getSession(false);
		// if (session != null) {
		// session.invalidate();
		// }
		request.logout();
	}

	// 免状态登录
	@PostMapping(value = "/api/customer/loginNoStaus")
	@ResponseBody
	public JsonObj loginCustomer(String login, String password) {

		// UsernamePasswordAuthenticationToken authRequest = new
		// UsernamePasswordAuthenticationToken(login, password);
		// // Authenticate the user
		// try {
		// // 验证
		// Authentication authentication =
		// authenticationManager.authenticate(authRequest);
		// SecurityContext securityContext = SecurityContextHolder.getContext();
		// securityContext.setAuthentication(authentication);
		// // Create a new session and add the security context.
		//
		// } catch (Exception e) {
		// return JsonObj.newErrorJsonObj("用户名或密码不存在");
		// }
		//
		// JsonObj resp = customerUserService.readByMobile(login, password);
		// if (resp.isSuccess()) {
		// return resp;
		// }

		return JsonObj.newErrorJsonObj("用户名或密码不存在");

	}

	// 免状态注销
	@RequestMapping(value = "/api/customer/logoutNostatus")
	public void logout() throws Exception {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// authentication.setAuthenticated(false);

	}

	// 修改密码
	@RequestMapping(value = "/api/customer/editPassword")
	public void editPassword() throws Exception {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// authentication.setAuthenticated(false);

	}

	// 重置密码
	@RequestMapping(value = "/api/customer/restPassword")
	public void restPassword() throws Exception {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// authentication.setAuthenticated(false);

	}
}
