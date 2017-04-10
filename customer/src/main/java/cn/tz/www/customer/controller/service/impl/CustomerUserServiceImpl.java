package cn.tz.www.customer.controller.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cn.tz.www.customer.controller.service.CustomerUserService;
import cn.tz.www.customer.entity.repository.customer.CustomerRepository;
import cn.tz.www.customer.entity.repository.customerRole.CustomerRoleRepository;
import cn.tz.www.customer.entity.table.Customer;
import cn.tz.www.customer.entity.table.CustomerRole;
import cn.tz.www.customer.entity.tools.JsonObj;
import cn.tz.www.customer.view.CustomerVo;

@Service
public class CustomerUserServiceImpl implements CustomerUserService {
	@Autowired(required = true)
	private CustomerRepository customerRepository;
	@Autowired(required = true)
	private CustomerRoleRepository customerRoleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public CustomerVo registCustomer(CustomerVo customerVo) {
		Customer cust = new Customer();
		cust.setLogin(customerVo.getLogin());
		cust.setPassword(passwordEncoder.encode(customerVo.getPassword()));
		cust.setLocked(false);

		List<CustomerRole> roleList = new ArrayList<CustomerRole>();
		roleList.add(customerRoleRepository.findByName("NORMAL"));
		cust.setCustomerRoles(roleList);
		customerRepository.save(cust);
		customerVo.setId(cust.getId());
		return customerVo;

	}

	public Boolean checkCustomer(String login) {
		Optional<Customer> list = customerRepository.findByLogin(login);
		if (list != null) {
			return false;
		} else {
			return true;
		}
	}

	public JsonObj readByMobile(String login, String password) {
		Customer user = customerRepository.findByMobile(login);
		if (user != null) {
			CustomerVo vo = new CustomerVo();
			vo.setId(user.getId());
			vo.setLogin(user.getLogin());
			vo.setPassword(password);
			return JsonObj.newSuccessJsonObj("登录成功", vo);
		}
		return JsonObj.newErrorJsonObj("用户名不存在");
	}
}
