package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import freemarker.template.TemplateException;

public class test implements ApplicationListener<ContextRefreshedEvent> {
	int a=1;
@Override
public void onApplicationEvent(ContextRefreshedEvent event) {

	if(event.getApplicationContext().getParent() == null){
	
		System.out.println("-------------------------"+(a++)+"----------------");
		}
}
public static void main(String[] args) throws IOException, TemplateException {
	  Map<String,Object> param = new HashMap<String, Object>();
	  param.put("path","111111");
	  System.out.println(MessageTempleteManager.process("test.ftl", param));

	 }
}

