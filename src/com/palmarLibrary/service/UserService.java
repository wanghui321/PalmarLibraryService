package com.palmarLibrary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.bean.Advice;
import com.palmarLibrary.bean.User;
import com.palmarLibrary.dao.SchoolDao;
import com.palmarLibrary.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private SchoolDao schoolDao;

	public boolean Register(User user,String schoolName) {
		String schoolId = schoolDao.getSchoolId(schoolName);
		boolean flag = userDao.judge(user,schoolId);
		if (flag) {
			boolean res = userDao.register(user);
			if (res) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean Settinger(User user) {


			boolean res = userDao.setting(user);
			if (res) {
				return true;
			}
			return false;
		
	}
	public boolean upload(User user) {
		boolean res = userDao.upload(user);
		if (res) {
			return true;
		}
		return false;
		
	};

	public String Login(User user, String schoolName) {
		String schoolId = schoolDao.getSchoolId(schoolName);
		String msg = userDao.Login(user,schoolId);
		return msg;
	}

	public User getUser(String userId) {
		User user = userDao.getUser(userId);
		return user;
	}

	public List<Map<String, Object>> getInterest(String userId) {
		// TODO Auto-generated method stub
		return userDao.getInterest(userId);
		
	}

	public boolean addAdvice(String userId, String advice, String time) {
		User user = userDao.getUser(userId);
		Advice advices = new Advice();
		advices.setUser(user);
		advices.setAdvice(advice);
		advices.setTime(time);
		userDao.addAdvice(advices);
		return true;
	}

}
