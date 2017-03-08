package com.mxiaixy.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import junit.framework.TestListener;

/**
 * 通过监听器赋值   监听器的实现类
 * @author Mxia
 *
 */
public class TaskListenerImpl implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		// 设置要赋予的变量值
		delegateTask.setAssignee("里斯");
	}

}
