package com.mxiaixy.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.h2.store.fs.FileUtils;
import org.junit.Test;

public class ProcessEngineTest {

	//创建数据库并获取 引擎 
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	
	/**
	 * 部署流程定义
	 */
	@Test
	public void deployment(){
		
		Deployment deployment = processEngine.getRepositoryService()//获取与流定义和部署对象相关的service
				.createDeployment()//创建一个部署对象
				.name("helloWorld部署开始")
				.addClasspathResource("diagrams/HelloWorld.bpmn")//获取资源中的文件
				.addClasspathResource("diagrams/Helloworld.png")
				.deploy();//完成部署
		
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:" + deployment.getName());
		
	}
	/**
	 * 以zip格式部署  主要是避免中文乱码
	 */
	@Test
	public void deployZip(){
		//将bpmn和png文件压缩曾zip文件或部署
		
		
		
		//获取输入流  获取文件位置
		InputStream inputStream =
				this.getClass().getClassLoader().getResourceAsStream("diagrams/HelloWorld.zip");
		//使用zip装饰着模式获取输入流
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		//部署对象
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("zip格式部署")
				.addZipInputStream(zipInputStream)
				.deploy();
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:"+deployment.getName());
		
	}
	/**
	 * 获取流程定义
	 */
	@Test
	public void getDefinition(){
		
		List<ProcessDefinition> definitionList =
				processEngine.getRepositoryService()//获取部署相关的service
				.createProcessDefinitionQuery()//获取流程定义查询
				.orderByProcessDefinitionId().asc()
				.list();
		
		for(ProcessDefinition pdf : definitionList){
			
			System.out.println("ID:"+pdf.getId());
			System.out.println("name:"+pdf.getName());
		}
		
	}
	
	/**
	 * 删除流程定义/部署  1.不级联删除
	 * 
	 * 只能删除没有启动的流程 否则会报错
	 */
	@Test
	public void delDefinition(){
		String deloymentId = "15009";
		processEngine.getRepositoryService().deleteDeployment(deloymentId);
		
		System.out.println("删除成功");
		
	}
	/**
	 * 级联删除   必须就有超级管理员权限
	 * 可以删除以启动的流程 并且是全部删除
	 */
	@Test
	public void delDefinitionAll(){
		String deloymentId="20001";
		processEngine.getRepositoryService().deleteDeployment(deloymentId,true);
		
		System.out.println("级联删除成功");
		
		
	}
	
	/**
	 * 查看png流程图
	 */
	@Test
	public void getPng() throws IOException{
		String definitionId="myProcess:4:12510";//流程定义id
		ProcessDefinition pdf = processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.processDefinitionId(definitionId)
				.singleResult();
		String deploymentId = pdf.getDeploymentId();//获取部署id
		String deployResource = pdf.getDiagramResourceName();//获取原png文件名称
		//获取文件输入流
		InputStream inputStream = processEngine.getRepositoryService()
				.getResourceAsStream(deploymentId, deployResource);
		//设置文件输出位置
		File file = new File("D:/HelloWorld.png");
		FileOutputStream outputStream = new FileOutputStream(file);
		//输出文件
		int len = -1;
		byte[] buffer =new byte[521];
		while((len=inputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, len);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		System.out.println("拷贝成功");
		
		
	}
	
	
	
	
	
	
	
}
