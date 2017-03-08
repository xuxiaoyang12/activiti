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
 * 排他网关 测试
 * @author Mxia
 *
 */
public class ExclusiveTest {

	//获取引擎
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程
	 */
	@Test
	public void deployment(){
		//部署一个流程
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
	 *  给予一个流程变量 并启动流程
	 */
	@Test
	public void start(){
		
		//添加流程变量
		Map<String,Object>  maps = new HashMap<>();
		maps.put("money", 600);
		
		//启动流程
		ProcessInstance processInstance =
				processEngine.getRuntimeService()
				.startProcessInstanceByKey("exclusive", maps);
		
		System.out.println("ID:"+processInstance.getId());
		System.out.println("Name:"+processInstance.getName());
		
	}
	
	
	
}
