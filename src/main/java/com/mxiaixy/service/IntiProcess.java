package com.mxiaixy.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * ������  �������ݿ�  ��ʾ 
 * @author Mxia
 *
 */
public class IntiProcess {

	/**
	 * �������ݿ�test1  �ֶ�������ʽ
	 */
	@Test
	public void  init(){
		
		//��������
		ProcessEngineConfiguration engine = 
				ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
				//����set�����޸�
		engine.setJdbcDriver("com.mysql.jdbc.Driver");
		engine.setJdbcUrl("jdbc:mysql:///activiti");
		engine.setJdbcUsername("root");
		engine.setJdbcPassword("root");
		
		//������ ��������� �Զ�������
		engine.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//�������ĺ��Ķ��� ProcessEngine����
		ProcessEngine processEngine = engine.buildProcessEngine();
		
		
		System.out.println("processEngine:"+processEngine);

		
	}
	/**
	 * �������ݿ�test2  ʹ��xml�ļ�
	 */
	@Test
	public void initTest2(){
		//������Ϊactiviti.cfg.xml�ļ���ͨ��processEngineConfiguration����processEngine ��ʼ�����ݿ�
		//��ʼ�����ݿ�
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		
		System.out.println("processEngine:"+processEngine);
		
	}
	
	/**
	 * �������ݿ�test3   �Զ�����
	 * 
	 * ����activiti.cfg.xml�ļ������ǲ��ܸĵ�  ���Կ���ʹ��Ĭ�ϵ��Զ�����ģʽ������
	 */
	
	@Test
	public void initTest3(){
		//����ProcessEngines��getDefaultProceeEngine�����ǻ��Զ�����classpath����Ϊactiviti.cfg.xml�ļ�
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		System.out.println("processEngine:" + processEngine);
	}
	
}
