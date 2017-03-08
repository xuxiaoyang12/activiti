package com.mxiaixy.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * 通过监听器 配置用户
 * @author Mxia
 *
 */
public class Task3 {
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程
	 */
	@Test
	public void deployment(){
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("task3监听器部署开始流程")
				.addClasspathResource("diagrams/task3.bpmn")
				.addClasspathResource("diagrams/task3.png")
				.deploy();
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:"+deployment.getName());
		
		
		
	}
	/**
	 * 启动流程
	 */
	@Test
	public void start(){
		
		processEngine.getRuntimeService()
		.startProcessInstanceByKey("task3");
		
		
		
		System.out.println("完成");
		
	}

	
	
}
