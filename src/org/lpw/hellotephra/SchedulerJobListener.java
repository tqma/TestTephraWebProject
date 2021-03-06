package org.lpw.hellotephra;

public interface SchedulerJobListener {
	    /**
	     * 开始执行定时器任务。
	     */
	    void begin();

	    /**
	     * 执行定时任务时发生异常。
	     *
	     * @param throwable 异常信息。
	     */
	    void exception(Throwable throwable);

	    /**
	     * 定时器任务执行完毕。
	     */
	    void finish();

}
