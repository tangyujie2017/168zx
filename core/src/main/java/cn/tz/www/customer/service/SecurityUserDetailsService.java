package cn.tz.www.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.tz.www.customer.entity.repository.user.UserRepository;
import cn.tz.www.customer.entity.table.Authority;
import cn.tz.www.customer.entity.table.Role;
import cn.tz.www.customer.entity.table.User;
@Service
public class SecurityUserDetailsService implements UserDetailsService{

	  @Autowired
	  private UserRepository userRepository;

	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    Optional<User> userOpt = userRepository.findByLogin(username);

	    User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

	    List<GrantedAuthority> auth = getGrantedAuthorities(user.getRoles());

	    String password = user.getPassword();

	    return new org.springframework.security.core.userdetails.User(username, password, auth);
	  }


	  public static List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    roles.forEach(r -> {
	    	List<Authority> auths = r.getAuthoritys();
	    	for(Authority auth : auths){
	    		authorities.add(new SimpleGrantedAuthority(auth.getCode()));
	    	}
	    	authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getName()));
	    });
	    return authorities;
	  }
}
