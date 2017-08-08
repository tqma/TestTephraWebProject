package org.lpw.interceptor;

import javax.inject.Inject;

import org.lpw.tephra.ctrl.Interceptor;
import org.lpw.tephra.ctrl.Invocation;
import org.lpw.tephra.ctrl.context.Request;
import org.springframework.stereotype.Controller;
@Controller("myInterceptor")
public class MyInterceptor implements Interceptor {
	@Inject
    private Request request;
	
	@Override
	public Object execute(Invocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n执行我的拦截器"+request.getUrl()+request.getUri()+"\n");
		System.out.println("\n执行我的拦截器"+request.getContextPath()+"\n");
		
		return invocation.invoke();
	}

	@Override
	public int getSort() {
		// TODO Auto-generated method stub
		return 0;
	}

}
