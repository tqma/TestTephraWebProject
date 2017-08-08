package org.lpw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.lpw.model.UserModel;
import org.lpw.tephra.dao.jdbc.SqlImpl;
import org.lpw.tephra.dao.model.Model;
import org.lpw.tephra.dao.orm.hibernate.HibernateOrmImpl;
import org.lpw.tephra.dao.orm.hibernate.HibernateQuery;
import org.springframework.stereotype.Repository;

@Repository("user.dao")
public class UserDao {

	@Inject
	private HibernateOrmImpl hi;
	@Inject
	private SqlImpl sl;
/*	@Inject
    private DataSource dataSource;*/

	public void addUser(UserModel u) {
//		javax.sql.DataSource ds = dataSource.getWriteable("");
//		hi.save(ds.getDefaultKey(), u);
		
		hi.save(u);		
//		throw new RuntimeException("测试错误！");

	}
	
	public UserModel findUserByName(UserModel u) {
//		javax.sql.DataSource ds = dataSource.getWriteable("");
//		hi.save(ds.getDefaultKey(), u);
		HibernateQuery hq = new HibernateQuery(UserModel.class);
//		hq.where("name = '" + u.getName() + "'");
		hq.where("name = ?");
		u = (UserModel) hi.query(hq, new Object[]{u.getName()}).getList().get(0);
//		u = (User) hi.query(hq, null).getList().get(0);
		System.out.println(u.getId()+"!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		throw new RuntimeException("测试错误！");
//		hi.close();
		return u;
	}
		
	
	public List<Model> findAllUser() {
		
		HibernateQuery hq = new HibernateQuery(UserModel.class);
		hq.size(5);
		hq.page(3);
		List<Model> l = new ArrayList<Model>();
		l = hi.query(hq,null).getList();
//		u = (User) hi.query(hq, null).getList().get(0);
		System.out.println("!!!!" + l.size()+"!!!!!!");
		return l;
	}	
	

	
	public void deleteUser(UserModel u) {

		hi.delete(u);
		
	}

	public void updateUserWithHibernate(UserModel u) {
//		u.setPassword("hello");
		hi.save(u);
		
	}
	
	public int[] updateUser(UserModel u) {
		List<Object[]> l = new ArrayList<Object[]>();
		l.add(new Object[]{"密码",u.getId()});
		int i[] = sl.update("update user set password =? where c_id = ?",l);
		sl.close();		
		return i;
	}
	
}
