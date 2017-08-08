package org.lpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.lpw.dao.UserDao;
import org.lpw.model.UserModel;
import org.lpw.tephra.dao.model.Model;
import org.lpw.tephra.dao.orm.hibernate.HibernateQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service("user.service")

public class UserService {
	@Inject
	private UserDao ud;
	
	public void addUser(UserModel u) {
		ud.addUser(u);		
	}

	public UserModel findUserByName(UserModel u) {
		return ud.findUserByName(u);		
	}
	
	public void addAndDeleteUser(UserModel u) {
		ud.addUser(u);
//		if (null != u) throw new RuntimeException("测试错误！");
		ud.deleteUser(u);
	}

	public void updateUser(UserModel u) {
		// TODO Auto-generated method stub
		ud.updateUser(u);
	}
	
	public void updateUserWithHibernate(UserModel u) {
		ud.updateUserWithHibernate(u);
	}
	
	public List<Model> findAllUser() {
		return ud.findAllUser();
	}	
}
