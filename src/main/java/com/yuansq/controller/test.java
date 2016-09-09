package com.yuansq.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class test implements ApplicationListener<ContextRefreshedEvent> {
	int a=1;
@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
	if(event.getApplicationContext().getParent() == null){
		System.out.println("-------------------------"+(a++)+"----------------");
		}
}
}

