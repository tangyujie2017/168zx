package cn.tz.www.customer.controller.service;

import cn.tz.www.customer.entity.tools.JsonObj;
import cn.tz.www.customer.view.CustomerVo;

public interface CustomerUserService {
	public Boolean checkCustomer(String login);
	public CustomerVo registCustomer(CustomerVo customer);
	public JsonObj readByMobile(String login,String password);
}
