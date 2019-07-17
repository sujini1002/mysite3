package com.cafe24.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.security.SecurityUser;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService{
	
	@Autowired
	private UserDao userDao;
	
	public Boolean existEmail(String email) {
		UserVo userVo = userDao.get(email);
		return userVo != null;
	}
	
	public UserService() {
		System.out.println("userService 생성");
	}

	public Boolean join(UserVo userVo) {
		return userDao.insert(userVo);
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo.getEmail(), userVo.getPassword());
	}
	
	public UserVo getUser(long no) {
		
		return userDao.get(no);
	}

	public Boolean update(UserVo updateUserVo) {
		return userDao.update(updateUserVo);
	}

	

}
