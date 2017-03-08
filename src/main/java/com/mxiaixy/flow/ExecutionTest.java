package com.mxiaixy.flow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * 并行网关
 * @author Mxia
 *
 */
public class ExecutionTest {
	
	//获取引擎
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程  并行网关
	 */
	@Test
	public void deploy(){
		
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("excution部署开始")
				.addClasspathResource("diagrams/execution.bpmn")
				.addClasspathResource("diagrams/execution.png")
				.deploy();
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:"+deployment.getName());
		System.out.println("time:"+deployment.getDeploymentTime());
		
		
	}
	
	/**
	 * 启动流程   任务节点的配置使用定量值  
	 * 如果使用变量值是 在设计diagrams时 把 assignee设置成${} 在使用流程变量传值的方式传入
	 */
	@Test
	public void start(){
		
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("execution");
		
		
		System.out.println("ID:"+processInstance.getId());
		System.out.println("name:"+processInstance.getName());
		System.out.println("key"+processInstance.getBusinessKey());
		
		
	}
	
	/**
	 * 并行网关中   完成个人任务  当所有并行网关中的任务完成通道才会关闭
	 */
	@Test
	public void completeTask(){
		//赵六
		String taskId = "30007";
		processEngine.getTaskService().complete(taskId);
		
		System.out.println("并行网关一方完成任务");
		
		
	}
	
	

	
}
