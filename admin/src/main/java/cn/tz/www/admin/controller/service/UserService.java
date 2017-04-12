package cn.tz.www.admin.controller.service;

import java.util.List;

import cn.tz.www.customer.entity.table.User;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.Page;

public interface UserService {
	public Boolean checkUserName(String login);
	public void createUser(User user)throws Exception;
	public Page<User> loadUserList(Groups groups, Page<User> page);
	
}
