package org.lpw.hellotephra;

import org.lpw.tephra.scheduler.MinuteJob;
import org.lpw.tephra.scheduler.SchedulerJob;
import org.springframework.stereotype.Controller;

//@Controller("myMinuteJob")
public class MyJob implements MinuteJob,SchedulerJob {

	@Override
	public void executeMinuteJob() {
		// TODO Auto-generated method stub
		p("ÿ������������������");
		p(".*��*.      ");
		p(".*�� *.*    ��");
		p("��       *");
		p("��           .��");
		p("��*.     .");
		p("    `");
		p("ÿ�������������");
	}

    private void p(Object o) {
		// TODO Auto-generated method stub
		System.err.println(o.toString());
	}

	@Override
	public void executeSchedulerJob() {
		// TODO Auto-generated method stub
		p("����ͨ��helper��ִ̬�еģ�");
		executeMinuteJob();
	}

	@Override
	public String getSchedulerName() {
		// TODO Auto-generated method stub
		return "myMinuteJob";
	}
    
}
