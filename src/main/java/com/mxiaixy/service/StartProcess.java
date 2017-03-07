package com.mxiaixy.service;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class StartProcess {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 启动流程
	 */
	@Test
	public void start(){
		//获取流程对象key  是diagrrams xml文件中定义的id
		String processDefinitionKey = "myProcess";
		ProcessInstance pi = processEngine.getRuntimeService()//获取流程实例对象
		.startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key来启动流程实例
		
		System.out.println("processInstance ID: "+pi.getId());
		System.out.println("name:"+pi.getName());
		
	}
	
	/**
	 * 查看个人任务
	 */
	@Test
	public void getInstance(){
		String assignee = "张三";
		List<Task> taskList = processEngine.getTaskService().createTaskQuery()//创建任务查询对象
				.taskAssignee(assignee)//委托人查询
		
				.orderByTaskCreateTime().asc()//按照创建时间的升序排列
				.list();//生声明结果对象
		
		for(Task task : taskList){
			
			System.out.println("task Name:"+task.getName());
		}
		
	}
	/**
	 * 完成个人任务
	 */
	@Test
	public void completeTask(){
		String assignee = "张三";
		String taskId="15002";//任务id
		
		processEngine.getTaskService().complete(taskId);
		//执行这个任务之后 会从act_ru_task中删除当前任务 并进入下一个任务 注意 id将改变
		System.out.println("审批完成");
		
	}
	
	
	
	
	
}
