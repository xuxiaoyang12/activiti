package com.mxiaixy.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 任务节点使用变量值
 * @author Mxia
 *
 */
public class ExecutionTask2 {

	
	//添加引擎
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	
	/**
	 * 部署流程    任务节点使用变量值
	 */
	@Test
	public void deployment(){
		
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("task2变量部署开始设置测试")
				.addClasspathResource("diagrams/task2.bpmn")
				.addClasspathResource("diagrams/task2.png")
				.deploy();
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("Name:"+deployment.getName());
	
			
	}
	
	/**
	 * 启动流程  赋予流程变量
	 */
	@Test
	public void start(){
		
		//添加变量id
		Map<String,Object> map = new HashMap<>();
		//userId为申请人部门经理的id  这里暂时由姓名代替  用来测试动态赋予变量名是否可行
		map.put("userId", "张三");
		
		//启动流程
		ProcessInstance pi = processEngine.getRuntimeService()
				.startProcessInstanceByKey("task2",map);
		
		System.out.println("ID:"+pi.getId());
		System.out.println("Name:"+pi.getName());
		System.out.println("流程定义id:"+pi.getProcessDefinitionId());
		
	}
	
	/**
	 * 获取任务列表
	 */
	@Test
	public void list(){
		
		String assignee = "张三";
		List<Task> taskList = processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.orderByTaskCreateTime().asc()
				.list();
		
		System.out.println("size:"+taskList.size());
		System.out.println("");
		
	}
	/**
	 * 完成个人任务
	 */
	@Test
	public void complete(){
		
		processEngine.getTaskService().complete("张三");
		System.out.println("task2完成任务");
		
	}
}
