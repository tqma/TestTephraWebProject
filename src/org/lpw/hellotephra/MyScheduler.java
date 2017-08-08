package org.lpw.hellotephra;

import org.lpw.tephra.scheduler.DateJob;
import org.lpw.tephra.scheduler.DateScheduler;
import org.lpw.tephra.scheduler.MinuteJob;
import org.lpw.tephra.scheduler.SchedulerSupport;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.util.Optional;
import java.util.Set;

/**
 * @author lpw
 */
@Component("MyScheduler")
public class MyScheduler extends SchedulerSupport<MinuteJob> implements DateScheduler {
    @Inject
    protected Optional<Set<MinuteJob>> jobs;

    @Scheduled(cron = "${tephra.scheduler.date.cron:1 * * * * ?}")
    @Override
    public synchronized void execute() {
        if (!jobs.isPresent())
            return;

        if (logger.isDebugEnable())
            logger.debug("���Լ��Ķ�ʱ����ʼ������");

        jobs.get().forEach(this::pool);

        if (logger.isDebugEnable())
            logger.debug("���Լ��Ķ�ʱ���ɹ�ִ��{}��ÿ�ն�ʱ������", jobs.get().size());
    }

    @Override
    public void execute(MinuteJob job) {
        
    	job.executeMinuteJob();;
    }

}