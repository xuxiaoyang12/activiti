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
 * �û�������  ����
 * 
 * ��һ����� ��������ͻ��������
 * @author Mxia
 *
 */
public class GroupTask2 {

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
					.name("�û���������ʼ2")
					.addClasspathResource("diagrams/GroupTask2.bpmn")
					.addClasspathResource("diagrams/GroupTask2.png")
					.deploy();
			
			System.out.println("ID:"+deployment.getId());
			System.out.println("Name:"+deployment.getName());
			
			
		}
		
		/**
		 * ��������
		 */
		@Test
		public void start(){
			//���ñ���ֵ
			Map<String,Object> map = new HashMap<>();
			map.put("userId", "��ޱ,����,rose");
			ProcessInstance processInstance = 
					processEngine.getRuntimeService()
					.startProcessInstanceByKey("groupTask2",map);
			
			System.out.println("ID:"+processInstance.getId());
			System.out.println("Name:"+processInstance.getName());
			System.out.println("���̶���id:"+processInstance.getProcessDefinitionId());
			
		}
		/**
		 * ��ȡ��������
		 */
		@Test
		public void taskOne(){
			String assignee = "��ޱ";
			
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
			String taskId = "57504";
			processEngine.getTaskService().complete(taskId);
			System.out.println("�������");
			
		}
	
}
