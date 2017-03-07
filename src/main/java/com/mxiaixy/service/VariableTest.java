package com.mxiaixy.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * 流程变量设置
 * @author Mxia
 *
 */
public class VariableTest {
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 流程变量添加  通过map集合添加
	 */
	@Test
	public void startVariables(){
		
		Map<String,Object> variables=new HashMap();
		variables.put("start","ok");
		variables.put("days", 5);
		
		//启动一个流程定义
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("myProcess", variables);
		
		System.out.println("name:"+processInstance.getName());
		System.out.println("ID:"+processInstance.getId());
		System.out.println(processInstance);

				
	}
	
	/**
	 * 单次单值设置流程变量    ???运行不了
	 */
	@Test
	public void singVariable(){
		//获取流程开始对象
		RuntimeService runtimeService = processEngine.getRuntimeService();
		//单次设置值
		runtimeService.setVariable("15004", "days", 20);//在数据库中已经创建
		//开始流程
	/*	ProcessInstance processInstance = 
				runtimeService.startProcessInstanceById("");
		
		System.out.println("name:"+processInstance.getName());
		System.out.println("ID:"+processInstance.getId());
		System.out.println(processInstance);
		*/
	}

	
	/**
	 * 获取 流程变量的值
	 */
	@Test
	public void getVariables(){
		
		
		//获取单次单值的值
		RuntimeService runtimeServie = processEngine.getRuntimeService();
		Integer days = (Integer)runtimeServie.getVariable("15004", "days");
		
		System.out.println(days);
		//获取map集合中的值
		Map<String,Object> variables=runtimeServie.getVariables("22501");
		
		System.out.println("days:"+variables.get("days"));
		System.out.println("start:"+variables.get("start"));
		
		
		
	}
}
