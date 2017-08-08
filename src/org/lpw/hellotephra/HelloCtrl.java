package org.lpw.hellotephra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.lpw.model.UserModel;
import org.lpw.service.UserService;
import org.lpw.tephra.bean.BeanFactory;
import org.lpw.tephra.cache.Cache;
import org.lpw.tephra.ctrl.context.Request;
import org.lpw.tephra.ctrl.context.Session;
import org.lpw.tephra.ctrl.execute.Execute;
import org.lpw.tephra.ctrl.template.Templates;
import org.lpw.tephra.ctrl.validate.Validate;
import org.lpw.tephra.ctrl.validate.Validators;
import org.lpw.tephra.dao.jdbc.DataSource;
import org.lpw.tephra.dao.model.Model;
import org.lpw.tephra.dao.model.ModelHelper;
import org.lpw.tephra.dao.model.ModelHelperImpl;
import org.lpw.tephra.nio.ClientManager;
import org.lpw.tephra.scheduler.SchedulerHelperImpl;
import org.springframework.stereotype.Controller;
import org.lpw.tephra.ctrl.validate.Validator;

import com.alibaba.fastjson.JSONObject;


@Controller("hellotephra.ctrl")
public class HelloCtrl {
    @Inject
    private UserService us;	
	@Inject
    private Request request;
    @Inject
    private BeanFactory bf;
    @Inject
    private Cache ce;
    
    @Inject
    protected ClientManager clientManager;
    @Inject
    private SchedulerHelperImpl si;
    @Inject
    private Session s;
    @Inject
    private ModelHelperImpl mh;
    @Inject
    private Templates templates;

    
    
	@Execute(name = "/hello",validates = {
			@Validate(validator = Validators.MAX_LENGTH, parameter = "name", number = {10}, failureCode = 1031, failureArgKeys = {"用户名"}),
			@Validate(validator = Validators.NOT_EMPTY, parameter = "name",failureCode = 1, failureArgKeys = {"用户名"}),
			@Validate(validator = Validators.BETWEEN, parameter = "name", number = {0,10},failureCode = 1, failureArgKeys = {"用户名"}),})
	
   public Object hello() {
    	
		
		
    	String beansName[] = bf.getBeanNames();
    	for (int i = 0; i < beansName.length; i++) {
			p(beansName[i]);
		}
    	
    	UserModel u = new UserModel();
    	u.setName(request.get("name"));
    	u.setPassword("password");
    	us.addUser(u);
    	
    	List<UserModel> users = s.get("users");
    	if (null == users) {
    		users = new ArrayList<UserModel>();
    		users.add(u);
    		s.set("users", users);
    	} else {
    		users.add(u);
    	}
    	
    	for (UserModel user : users) {
    		System.out.println(user.getId()+"	"+user.getName()+"	"+user.getPassword());
    	}
    	
    	ce.put("cuser", u, true);
//    	List<HelloCtrl> o = new ArrayList<HelloCtrl>();
    	Collection o =  BeanFactory.getBeans(DataSource.class);
    	
    	Iterator it = o.iterator();
    	int i = 0;
    	p(o.size());
    	while (it.hasNext()) {
    		DataSource h = (DataSource) it.next();
    		i++;
    		p("========��"+i+"��" + h.getClass().getName() +"������"+ beanNameOf(h));
    	}

    	return "hello2 " + request.get("name")+" c_id:"+u.getId();
 /*   	Client client = clientManager.get();
    	
    	MinuteSchedulerImpl m = BeanFactory.getBean("tephra.scheduler.minute");
    	
    	p("======================"+beanNameOf(si));
    	Date d = new Date();
    	d.setMinutes(48);
    	p(d.toString());
    	si.at(BeanFactory.getBean("myMinuteJob"), d);
//    	si.delay(BeanFactory.getBean("myMinuteJob"), 20);
    	d.setMinutes(49);
    	p(d.toString());
    	si.at(BeanFactory.getBean("myMinuteJob"), d);*/
    	
        
    }

    private String beanNameOf(Object bean) {
		// TODO Auto-generated method stub
    	String beanNames[] = BeanFactory.getBeanNames();
    	if (null != beanNames && beanNames.length>0) {
    		for (int i = 0; i < beanNames.length; i++) {
				Object o = BeanFactory.getBean(beanNames[i]);
    			if (bean == o) {
					return beanNames[i];
				}
			}    		
    	}
    	return null;
	}

	private void p(Object o) {
		// TODO Auto-generated method stub
		System.err.println(o.toString());
	}

	@Execute(name = "/hi", type = "freemarker", template = "hi")
    public Object hi() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", request.get("name"));
 
        return map;
    }
	
	@Execute(name = "/f", type = "freemarker", template = "hi2")
    public Object f() {
    	List<UserModel> users = s.get("users");
    	Map<String, List<UserModel>> users2 = new HashMap<String, List<UserModel>>();
    	users2.put("users2", users);
        return users2;
    }
	
	@Execute(name = "/d")
    public Object d() {
    	UserModel u = new UserModel();
    	u.setName(request.get("name"));
    	u.setPassword("password");
    	us.addAndDeleteUser(u);
    	return "gg";
    }
	
	@Execute(name = "/find")
    public Object find() {
    	UserModel u = new UserModel();
    	u.setName(request.get("name"));
    	u = us.findUserByName(u);

    	JSONObject jsonu = mh.toJson(u);
    	
    	System.out.println(jsonu.toJSONString());
    	System.out.println(jsonu.toString());
    	return u.getId()+ "   " + u.getName() + "   "+ u.getPassword()+ "   "+ jsonu.toJSONString();
    }

	@Execute(name = "/cuser")
    public Object getCUser() {
    	UserModel u = ce.get("cuser");    	
    	return u.getName();
    }	

	@Execute(name = "/updateuser")
    public Object updateUser() {
		UserModel u = new UserModel();
    	u.setName(request.get("name"));
		u = us.findUserByName(u);
//    	us.updateUser(u);
    	us.updateUserWithHibernate(u);
    	System.out.println(u.getId()+ "   " + u.getName() + "   "+ u.getPassword());
    	
    	UserModel u2 = new UserModel();
    	u2.setName(request.get("name"));
    	u2 = us.findUserByName(u2);
    	System.out.println(u2.getPassword());
    	System.out.println(u2.getId()+ "   " + u2.getName() + "   "+ u2.getPassword());
    	return  u2.getId()+ "   " + u2.getName() + "   "+ u2.getPassword();
    }	
	
	@Execute(name = "/alluser", type = "freemarker", template = "hi2")
    public Object alluser() {
		List<Model> users = us.findAllUser();
    	Map<String, List<Model>> users2 = new HashMap<String, List<Model>>();
    	users2.put("users2", users);
        return users2;
    }
}