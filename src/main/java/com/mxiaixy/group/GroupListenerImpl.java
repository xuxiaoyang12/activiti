package com.mxiaixy.group;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class GroupListenerImpl implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		delegateTask.setAssignee("’‘ﬁ±");
		delegateTask.setAssignee("¡Ô¡Ô");
		delegateTask.setAssignee("∫£—Û");
		
	}

}
