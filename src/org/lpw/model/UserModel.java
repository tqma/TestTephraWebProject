package org.lpw.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.config.BeanDefinition;
import org.lpw.tephra.dao.model.DataSource;
import org.lpw.tephra.dao.model.Jsonable;
import org.lpw.tephra.dao.model.ModelSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user.model")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Entity(name = "user")
//@DataSource(key="")
@Table(name = "user")
public class UserModel extends ModelSupport{
	private String name;
	private String password;
	
	@Jsonable
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	@Jsonable
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
