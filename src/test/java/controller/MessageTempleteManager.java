package controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MessageTempleteManager {
	 private static MessageTempleteManager tplm = null;
	 private Configuration cfg = null;

	 private MessageTempleteManager() {
	  cfg = new Configuration();
	  try {
	   cfg.setClassForTemplateLoading(this.getClass(), "/controller");
	  } catch (Exception e) {
	   
	  }
	 }

	 private static Template getTemplate(String name) throws IOException {
	  if(tplm == null) {
	   tplm = new MessageTempleteManager();
	  }
	  return tplm.cfg.getTemplate(name);
	 }
	 
	 public static String process(String templatefile, Map<?, ?> param) throws IOException, TemplateException{
	  Template template=MessageTempleteManager.getTemplate(templatefile);
	  StringWriter sw = new StringWriter();
	  template.process(param, sw);
	  return sw.toString();
	 } 
	
}
