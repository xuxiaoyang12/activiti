package com.mxiaixy.group;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * �û�������  ����������
 * @author Mxia
 *
 */
public class GroupTask3 {

	//��ȡ����
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		/**
		 * ��������
		 */
		@Test
		public void deployment(){
			Deployment deployment =
					processEngine.getRepositoryService()
					.createDeployment()
					.name("�û���������ʼ3")
					.addClasspathResource("diagrams/GroupTask3.bpmn")
					.addClasspathResource("diagrams/GroupTask3.png")
					.deploy();
			
			System.out.println("ID:"+deployment.getId());
			System.out.println("Name:"+deployment.getName());
			
			
		}
		
		/**
		 * ��������
		 */
		@Test
		public void start(){
			
			ProcessInstance processInstance = 
					processEngine.getRuntimeService()
					.startProcessInstanceByKey("groupTask3");
			
			System.out.println("ID:"+processInstance.getId());
			System.out.println("Name:"+processInstance.getName());
			System.out.println("���̶���id:"+processInstance.getProcessDefinitionId());
			
		}
		/**
		 * ��ȡ��������
		 */
		@Test
		public void taskOne(){
			String assignee = "����";
			//processEngine.getTaskService().claim("70004", "��ޱ");
			
			
			
			List<Task> taskList = processEngine.getTaskService()
					.createTaskQuery()
					.orderByTaskCreateTime().asc()
					.taskCandidateUser(assignee)
					.list();
			
			System.out.println("size:"+taskList.size());
			
			
		}
		/**
		 * �������
		 */
		@Test
		public void complete(){
			
			String assignee = "����";
			String taskId = "70004";
			processEngine.getTaskService().complete(taskId);
			System.out.println("�������");
			
		}
	
}
