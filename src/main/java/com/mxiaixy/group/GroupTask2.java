package com.mxiaixy.group;

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
 * 用户组任务  变量
 * 
 * 有一个完成 整个任务就会结束运行
 * @author Mxia
 *
 */
public class GroupTask2 {

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
					.name("用户组任务部署开始2")
					.addClasspathResource("diagrams/GroupTask2.bpmn")
					.addClasspathResource("diagrams/GroupTask2.png")
					.deploy();
			
			System.out.println("ID:"+deployment.getId());
			System.out.println("Name:"+deployment.getName());
			
			
		}
		
		/**
		 * 启动流程
		 */
		@Test
		public void start(){
			//设置变量值
			Map<String,Object> map = new HashMap<>();
			map.put("userId", "赵薇,刘海,rose");
			ProcessInstance processInstance = 
					processEngine.getRuntimeService()
					.startProcessInstanceByKey("groupTask2",map);
			
			System.out.println("ID:"+processInstance.getId());
			System.out.println("Name:"+processInstance.getName());
			System.out.println("流程定义id:"+processInstance.getProcessDefinitionId());
			
		}
		/**
		 * 获取个人任务
		 */
		@Test
		public void taskOne(){
			String assignee = "赵薇";
			
			List<Task> taskList = processEngine.getTaskService()
					.createTaskQuery()
					.orderByTaskCreateTime().asc()
					.taskCandidateUser(assignee)
					.list();
			
			System.out.println("size:"+taskList.size());
			
			
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
