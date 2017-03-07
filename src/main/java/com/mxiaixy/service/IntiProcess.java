package com.mxiaixy.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * 工作流  创建数据库  演示 
 * @author Mxia
 *
 */
public class IntiProcess {

	/**
	 * 创建数据库test1  手动创建形式
	 */
	@Test
	public void  init(){
		
		//创建引擎
		ProcessEngineConfiguration engine = 
				ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
				//根据set方法修改
		engine.setJdbcDriver("com.mysql.jdbc.Driver");
		engine.setJdbcUrl("jdbc:mysql:///activiti");
		engine.setJdbcUsername("root");
		engine.setJdbcPassword("root");
		
		//创建表 如果表不存在 自动创建表
		engine.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//工作流的核心对象 ProcessEngine对象
		ProcessEngine processEngine = engine.buildProcessEngine();
		
		
		System.out.println("processEngine:"+processEngine);

		
	}
	/**
	 * 创建数据库test2  使用xml文件
	 */
	@Test
	public void initTest2(){
		//创建名为activiti.cfg.xml文件并通过processEngineConfiguration加载processEngine 初始化数据库
		//初始化数据库
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		
		System.out.println("processEngine:"+processEngine);
		
	}
	
	/**
	 * 创建数据库test3   自动创建
	 * 
	 * 由于activiti.cfg.xml文件名称是不能改的  所以可以使用默认的自动创建模式来创建
	 */
	
	@Test
	public void initTest3(){
		//调用ProcessEngines的getDefaultProceeEngine方法是会自动创建classpath下名为activiti.cfg.xml文件
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		System.out.println("processEngine:" + processEngine);
	}
	
}
