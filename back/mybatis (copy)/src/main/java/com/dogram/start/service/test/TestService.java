package com.dogram.start.service.test;


import java.io.InputStream;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.User;

@Service
public class TestService {
	
	@Inject
	private SqlSessionFactory factory;

	@Autowired
	private TestMapper mapper;
	
	public String selectNow() {
		return mapper.selectNow();
	}
	
	public User insertUser(User user) {

		return mapper.insertUser(user);
	}
	
}
