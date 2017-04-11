package cn.tz.www.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.tz.www.customer.entity.repository.customer.CustomerRepository;
import cn.tz.www.customer.entity.table.Customer;
import cn.tz.www.customer.entity.table.CustomerAuthority;
import cn.tz.www.customer.entity.table.CustomerRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 */
@Service
public class SecurityCustomerDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Customer> userOpt = customerRepository.findByLogin(username);

		Customer user = userOpt.orElseThrow(() -> new UsernameNotFoundException("用户名不存在: " + username));

		List<GrantedAuthority> auth = getGrantedAuthorities(user.getCustomerRoles());

		String password = user.getPassword();

		return new org.springframework.security.core.userdetails.User(username, password, auth);
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<CustomerRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(r -> {
			List<CustomerAuthority> auths = r.getAuthoritys();
			for (CustomerAuthority auth : auths) {
				authorities.add(new SimpleGrantedAuthority(auth.getCode()));
			}
			authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
		});
		return authorities;
	}
}