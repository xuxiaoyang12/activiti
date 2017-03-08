package com.mxiaixy.flow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * ��������
 * @author Mxia
 *
 */
public class ExecutionTest {
	
	//��ȡ����
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * ��������  ��������
	 */
	@Test
	public void deploy(){
		
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("excution����ʼ")
				.addClasspathResource("diagrams/execution.bpmn")
				.addClasspathResource("diagrams/execution.png")
				.deploy();
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:"+deployment.getName());
		System.out.println("time:"+deployment.getDeploymentTime());
		
		
	}
	
	/**
	 * ��������   ����ڵ������ʹ�ö���ֵ  
	 * ���ʹ�ñ���ֵ�� �����diagramsʱ �� assignee���ó�${} ��ʹ�����̱�����ֵ�ķ�ʽ����
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
	 * ����������   ��ɸ�������  �����в��������е��������ͨ���Ż�ر�
	 */
	@Test
	public void completeTask(){
		//����
		String taskId = "30007";
		processEngine.getTaskService().complete(taskId);
		
		System.out.println("��������һ���������");
		
		
	}
	
	

	
}
