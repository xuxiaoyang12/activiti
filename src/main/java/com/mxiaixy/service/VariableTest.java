package com.mxiaixy.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * ���̱�������
 * @author Mxia
 *
 */
public class VariableTest {
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * ���̱������  ͨ��map�������
	 */
	@Test
	public void startVariables(){
		
		Map<String,Object> variables=new HashMap();
		variables.put("start","ok");
		variables.put("days", 5);
		
		//����һ�����̶���
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("myProcess", variables);
		
		System.out.println("name:"+processInstance.getName());
		System.out.println("ID:"+processInstance.getId());
		System.out.println(processInstance);

				
	}
	
	/**
	 * ���ε�ֵ�������̱���    ???���в���
	 */
	@Test
	public void singVariable(){
		//��ȡ���̿�ʼ����
		RuntimeService runtimeService = processEngine.getRuntimeService();
		//��������ֵ
		runtimeService.setVariable("15004", "days", 20);//�����ݿ����Ѿ�����
		//��ʼ����
	/*	ProcessInstance processInstance = 
				runtimeService.startProcessInstanceById("");
		
		System.out.println("name:"+processInstance.getName());
		System.out.println("ID:"+processInstance.getId());
		System.out.println(processInstance);
		*/
	}

	
	/**
	 * ��ȡ ���̱�����ֵ
	 */
	@Test
	public void getVariables(){
		
		
		//��ȡ���ε�ֵ��ֵ
		RuntimeService runtimeServie = processEngine.getRuntimeService();
		Integer days = (Integer)runtimeServie.getVariable("15004", "days");
		
		System.out.println(days);
		//��ȡmap�����е�ֵ
		Map<String,Object> variables=runtimeServie.getVariables("22501");
		
		System.out.println("days:"+variables.get("days"));
		System.out.println("start:"+variables.get("start"));
		
		
		
	}
}
