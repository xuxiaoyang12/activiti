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

	//�������ݿⲢ��ȡ ���� 
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	
	/**
	 * �������̶���
	 */
	@Test
	public void deployment(){
		
		Deployment deployment = processEngine.getRepositoryService()//��ȡ��������Ͳ��������ص�service
				.createDeployment()//����һ���������
				.name("helloWorld����ʼ")
				.addClasspathResource("diagrams/HelloWorld.bpmn")//��ȡ��Դ�е��ļ�
				.addClasspathResource("diagrams/Helloworld.png")
				.deploy();//��ɲ���
		
		
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:" + deployment.getName());
		
	}
	/**
	 * ��zip��ʽ����  ��Ҫ�Ǳ�����������
	 */
	@Test
	public void deployZip(){
		//��bpmn��png�ļ�ѹ����zip�ļ�����
		
		
		
		//��ȡ������  ��ȡ�ļ�λ��
		InputStream inputStream =
				this.getClass().getClassLoader().getResourceAsStream("diagrams/HelloWorld.zip");
		//ʹ��zipװ����ģʽ��ȡ������
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		//�������
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment()
				.name("zip��ʽ����")
				.addZipInputStream(zipInputStream)
				.deploy();
		System.out.println("ID:"+deployment.getId());
		System.out.println("name:"+deployment.getName());
		
	}
	/**
	 * ��ȡ���̶���
	 */
	@Test
	public void getDefinition(){
		
		List<ProcessDefinition> definitionList =
				processEngine.getRepositoryService()//��ȡ������ص�service
				.createProcessDefinitionQuery()//��ȡ���̶����ѯ
				.orderByProcessDefinitionId().asc()
				.list();
		
		for(ProcessDefinition pdf : definitionList){
			
			System.out.println("ID:"+pdf.getId());
			System.out.println("name:"+pdf.getName());
		}
		
	}
	
	/**
	 * ɾ�����̶���/����  1.������ɾ��
	 * 
	 * ֻ��ɾ��û������������ ����ᱨ��
	 */
	@Test
	public void delDefinition(){
		String deloymentId = "15009";
		processEngine.getRepositoryService().deleteDeployment(deloymentId);
		
		System.out.println("ɾ���ɹ�");
		
	}
	/**
	 * ����ɾ��   ������г�������ԱȨ��
	 * ����ɾ�������������� ������ȫ��ɾ��
	 */
	@Test
	public void delDefinitionAll(){
		String deloymentId="20001";
		processEngine.getRepositoryService().deleteDeployment(deloymentId,true);
		
		System.out.println("����ɾ���ɹ�");
		
		
	}
	
	/**
	 * �鿴png����ͼ
	 */
	@Test
	public void getPng() throws IOException{
		String definitionId="myProcess:4:12510";//���̶���id
		ProcessDefinition pdf = processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.processDefinitionId(definitionId)
				.singleResult();
		String deploymentId = pdf.getDeploymentId();//��ȡ����id
		String deployResource = pdf.getDiagramResourceName();//��ȡԭpng�ļ�����
		//��ȡ�ļ�������
		InputStream inputStream = processEngine.getRepositoryService()
				.getResourceAsStream(deploymentId, deployResource);
		//�����ļ����λ��
		File file = new File("D:/HelloWorld.png");
		FileOutputStream outputStream = new FileOutputStream(file);
		//����ļ�
		int len = -1;
		byte[] buffer =new byte[521];
		while((len=inputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, len);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		System.out.println("�����ɹ�");
		
		
	}
	
	
	
	
	
	
	
}
