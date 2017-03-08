package com.mxiaixy.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * ͨ�������� �����û�
 * @author Mxia
 *
 */
public class Task3 {
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * ��������
	 */
	@Test
	public void deployment(){
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("task3����������ʼ����")
				.addClasspathResource("diagrams/task3.bpmn")
				.addClasspathResource("diagrams/task3.png")
				.deploy();
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:"+deployment.getName());
		
		
		
	}
	/**
	 * ��������
	 */
	@Test
	public void start(){
		
		processEngine.getRuntimeService()
		.startProcessInstanceByKey("task3");
		
		
		
		System.out.println("���");
		
	}

	
	
}
