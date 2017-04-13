package cn.tz.www.admin.controller.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tz.www.admin.controller.service.UserService;
import cn.tz.www.customer.entity.repository.user.UserRepository;
import cn.tz.www.customer.entity.table.User;
import cn.tz.www.customer.entity.tools.Groups;
import cn.tz.www.customer.entity.tools.Page;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Boolean checkUserName(String login) {
		Optional<User> userOpt = userRepository.findByLogin(login);
		if (userOpt.isPresent()) {
			return false;
		}
		return true;
	}

	@Transactional
	public void createUser(User user) throws Exception {
		userRepository.persist(user);
	}

	public Page<User> loadUserList(Groups groups, Page<User> page) {
		return userRepository.findEntityPageByGroups(groups, page);
		

	}
	
	public User loadUserByLogin(String login){
		
		return userRepository.findByLogin(login).get();
	}
}
