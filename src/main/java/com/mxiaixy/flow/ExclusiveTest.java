package com.mxiaixy.flow;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * �������� ����
 * @author Mxia
 *
 */
public class ExclusiveTest {

	//��ȡ����
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * ��������
	 */
	@Test
	public void deployment(){
		//����һ������
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("exclusive")
				.addClasspathResource("diagrams/Exclusive.bpmn")
				.addClasspathResource("diagrams/Exclusive.png")
				.deploy();
		
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("Name:"+deployment.getName());
		
		
	}
	
	
	/**
	 *  ����һ�����̱��� ����������
	 */
	@Test
	public void start(){
		
		//������̱���
		Map<String,Object>  maps = new HashMap<>();
		maps.put("money", 600);
		
		//��������
		ProcessInstance processInstance =
				processEngine.getRuntimeService()
				.startProcessInstanceByKey("exclusive", maps);
		
		System.out.println("ID:"+processInstance.getId());
		System.out.println("Name:"+processInstance.getName());
		
	}
	
	
	
}
