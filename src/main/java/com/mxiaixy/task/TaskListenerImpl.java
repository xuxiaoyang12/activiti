package com.mxiaixy.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import junit.framework.TestListener;

/**
 * ͨ����������ֵ   ��������ʵ����
 * @author Mxia
 *
 */
public class TaskListenerImpl implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		// ����Ҫ����ı���ֵ
		delegateTask.setAssignee("��˹");
	}

}
