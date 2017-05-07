package cn.tz.www.admin.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tz.www.admin.controller.service.UserService;
import cn.tz.www.admin.controller.util.PageParam;
import cn.tz.www.admin.controller.util.PageVo;
import cn.tz.www.customer.entity.table.User;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.JsonObj;
import cn.tz.www.customer.entity.tools.Page;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 检查用户名是否存在
	@RequestMapping(value = "/user/check")
	@ResponseBody
	public JsonObj checkCustomer(String login, Principal principal) {
		if (principal == null) {
			JsonObj.newErrorJsonObj("用户信息过期请重新登录");
		}
		return JsonObj.newSuccessJsonObj("检测成功", userService.checkUserName(login));
	}

	@PostMapping(value = "/user/create")
	@ResponseBody
	public JsonObj createUser(User user, Principal principal) {
		if (principal == null) {
			JsonObj.newErrorJsonObj("用户信息过期请重新登录");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userService.createUser(user);
		} catch (Exception e) {
			JsonObj.newErrorJsonObj("创建用户失败");
			e.printStackTrace();
		}
		return JsonObj.newSuccessJsonObj("创建用户成功");
	}

	@RequestMapping(value = "/user/List")
	@ResponseBody
	public PageVo<User> createUser(@Valid PageParam param, BindingResult result, Principal principal) {
		if (principal == null) {
			JsonObj.newErrorJsonObj("用户信息过期请重新登录");
		}
		int pageSize = param.getPageSize();
		int currentPage = param.getPageIndex();
		// ProviderGoodsQuery<Commodity> query = new ProviderGoodsQuery<>();
		// Groups groups = query.toQueryGroups(param.getSearchParam());
		Groups groups = new Groups();
		groups.setOrderby("createTime");
		groups.setOrder(false);
		Page<User> page = new Page<User>(pageSize, currentPage);
		userService.loadUserList(groups, page);
		PageVo<User> vo = new PageVo<>();
		vo.setTotal(page.getTotalCount());
		//vo.setPicPath(picBasePath);
		vo.setItems(page.getItems());
		return vo;
	}
	//修改密码
	@RequestMapping(value = "/user/modify")
	@ResponseBody
	public PageVo<User> modify(Principal principal) {
		if (principal == null) {
			JsonObj.newErrorJsonObj("用户信息过期请重新登录");
		}
		
		return null;
	}

}
