package org.lpw.hellotephra;

public interface SchedulerJobListener {
	    /**
	     * ��ʼִ�ж�ʱ������
	     */
	    void begin();

	    /**
	     * ִ�ж�ʱ����ʱ�����쳣��
	     *
	     * @param throwable �쳣��Ϣ��
	     */
	    void exception(Throwable throwable);

	    /**
	     * ��ʱ������ִ����ϡ�
	     */
	    void finish();

}
