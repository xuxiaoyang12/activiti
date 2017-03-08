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
 * ����ڵ�ʹ�ñ���ֵ
 * @author Mxia
 *
 */
public class ExecutionTask2 {

	
	//�������
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	
	/**
	 * ��������    ����ڵ�ʹ�ñ���ֵ
	 */
	@Test
	public void deployment(){
		
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("task2��������ʼ���ò���")
				.addClasspathResource("diagrams/task2.bpmn")
				.addClasspathResource("diagrams/task2.png")
				.deploy();
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("Name:"+deployment.getName());
	
			
	}
	
	/**
	 * ��������  �������̱���
	 */
	@Test
	public void start(){
		
		//��ӱ���id
		Map<String,Object> map = new HashMap<>();
		//userIdΪ�����˲��ž����id  ������ʱ����������  �������Զ�̬����������Ƿ����
		map.put("userId", "����");
		
		//��������
		ProcessInstance pi = processEngine.getRuntimeService()
				.startProcessInstanceByKey("task2",map);
		
		System.out.println("ID:"+pi.getId());
		System.out.println("Name:"+pi.getName());
		System.out.println("���̶���id:"+pi.getProcessDefinitionId());
		
	}
	
	/**
	 * ��ȡ�����б�
	 */
	@Test
	public void list(){
		
		String assignee = "����";
		List<Task> taskList = processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.orderByTaskCreateTime().asc()
				.list();
		
		System.out.println("size:"+taskList.size());
		System.out.println("");
		
	}
	/**
	 * ��ɸ�������
	 */
	@Test
	public void complete(){
		
		processEngine.getTaskService().complete("����");
		System.out.println("task2�������");
		
	}
}
