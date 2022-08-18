package com.Ecommerce.JWT.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Ecommerce.JWT.Repo.JWTRepo;
import com.Ecommerce.JWT.model.DAOUser;
import com.Ecommerce.JWT.model.UserDTO;

import java.util.ArrayList;

@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	private  JWTRepo  userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("JWT_Demo1".equals(username)) {
//			return new User("JWT_Demo1", "$2a$10$yjsxfsfMZSbPZiTj21YgdevfE2OstrJl08WxjsBH6t.CeoTQk46Ru",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}
	@Override
	public UserDetails loadUserByUsername(String username)  {
		DAOUser d = userDao.findByusername(username); 
		return new org.springframework.security.core.userdetails.User(d.getUsername(),d.getPassword(),new ArrayList<>());
		
	}
	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}
}
