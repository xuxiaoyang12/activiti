package com.mxiaixy.service;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.junit.Test;

public class HistoryProcess {

	//��ȡ����
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	
	/**
	 * ��ѯ��ʷ����ʵ��
	 */
	@Test
	public void queryHistoryProcess(){
		
		HistoricProcessInstance hai = processEngine.getHistoryService()
				.createHistoricProcessInstanceQuery()
				.processInstanceId("15004")
				.singleResult();
		//System.out.println("name:"+hai.getName());
		System.out.println(hai);
		System.out.println("name:"+hai.getName());
		System.out.println("id:"+hai.getId());
		System.out.println("key:"+hai.getBusinessKey());
		
		
	}
	
	/**
	 * ��ѯ��ʷ����id  list
	 */
	@Test
	public void historyList(){
		List<HistoricProcessInstance> hpiList = 
				processEngine.getHistoryService()
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey("myProcess")
				.list();
		
		for(HistoricProcessInstance hpi:hpiList){
			
			System.out.println("name:"+hpi.getName());
			System.out.println("ID:"+hpi.getId());
		}
		
	}
}
