package com.mxiaixy.group;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 用户组  任务   定量 测试
 * @author Mxia
 *
 */
public class GroupTask1 {

	//获取引擎
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程
	 */
	@Test
	public void deployment(){
		Deployment deployment =
				processEngine.getRepositoryService()
				.createDeployment()
				.name("用户组任务部署开始")
				.addClasspathResource("diagrams/GroupTask1.bpmn")
				.addClasspathResource("diagrams/GroupTask1.png")
				.deploy();
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("Name:"+deployment.getName());
		
		
	}
	
	/**
	 * 启动流程
	 */
	@Test
	public void start(){
		
		ProcessInstance processInstance = 
				processEngine.getRuntimeService()
				.startProcessInstanceByKey("groupTask1");
		
		System.out.println("ID:"+processInstance.getId());
		System.out.println("Name:"+processInstance.getName());
		System.out.println("流程定义id:"+processInstance.getProcessDefinitionId());
		
	}
	/**
	 * 获取个人任务
	 */
	@Test
	public void taskOne(){
		String assignee = "赵六";
		
		List<Task> taskList = processEngine.getTaskService()
				.createTaskQuery()
				.orderByTaskCreateTime().asc()
				.taskCandidateUser(assignee)
				.list();
		
		System.out.println("size:"+taskList.size());
		
		
	}
	/**
	 *领取任务
	 */
	@Test
	public void chime(){
		
		processEngine.getTaskService().claim("2504", "王五");
		System.out.println("完成领取");
		
	}
	/**
	 * 完成任务
	 */
	@Test
	public void complete(){
		
		String assignee = "吕七";
		String taskId = "57504";
		processEngine.getTaskService().complete(taskId);
		System.out.println("完成任务");
		
	}
}
