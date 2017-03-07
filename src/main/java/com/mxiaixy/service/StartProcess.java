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
	 * ��������
	 */
	@Test
	public void start(){
		//��ȡ���̶���key  ��diagrrams xml�ļ��ж����id
		String processDefinitionKey = "myProcess";
		ProcessInstance pi = processEngine.getRuntimeService()//��ȡ����ʵ������
		.startProcessInstanceByKey(processDefinitionKey);//ʹ�����̶����key����������ʵ��
		
		System.out.println("processInstance ID: "+pi.getId());
		System.out.println("name:"+pi.getName());
		
	}
	
	/**
	 * �鿴��������
	 */
	@Test
	public void getInstance(){
		String assignee = "����";
		List<Task> taskList = processEngine.getTaskService().createTaskQuery()//���������ѯ����
				.taskAssignee(assignee)//ί���˲�ѯ
		
				.orderByTaskCreateTime().asc()//���մ���ʱ�����������
				.list();//�������������
		
		for(Task task : taskList){
			
			System.out.println("task Name:"+task.getName());
		}
		
	}
	/**
	 * ��ɸ�������
	 */
	@Test
	public void completeTask(){
		String assignee = "����";
		String taskId="15002";//����id
		
		processEngine.getTaskService().complete(taskId);
		//ִ���������֮�� ���act_ru_task��ɾ����ǰ���� ��������һ������ ע�� id���ı�
		System.out.println("�������");
		
	}
	
	
	
	
	
}
