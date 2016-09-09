package com.yuansq.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuansq.util.JsonUtil;

@Controller
public class HelloTest {
	@RequestMapping("/hello")
	public String toIndex(HttpServletRequest request, Model model) {
		model.addAttribute("hello", "hello");
		System.out.println("hello!");
		return "test1";
	}
	@RequestMapping("/hello2")
	public String toIndex3(HttpServletRequest request, Model model) {
		model.addAttribute("hello2", "hello2");
		System.out.println("hello2!");
		return "angular";
	}
	@RequestMapping("/hello3")
	public String toIndex2(HttpServletRequest request, Model model) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("emailId", "501258834@qq.com");
		map.put("userName", "yuansq");
	
		model.addAttribute("user", map);
		System.out.println("hello2!");
		return "/kid/hello2";
	}
	@RequestMapping("/angularKid")
	public String angularKid(HttpServletRequest request, Model model) {
		
		return "/kid/hello";
	}
	@RequestMapping(value="/ajax",method = RequestMethod.GET)
	public String hello(HttpServletRequest request, Model model) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("emailId", "501258834@qq.com");
		map.put("userName", "yuansq");
	
		model.addAttribute("user", map);
		System.out.println("hello2!");
		return "/kid/hello2";
	}
	@RequestMapping(value="/angular",method = {RequestMethod.POST})
	  public  @ResponseBody String getUser() {
		System.out.println("hello,angjularjs!");
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("emailId", "501258834@qq.com");
		map.put("userName", "yuansq");
	    return JsonUtil.getJsonAsString(map);
	  }
	@RequestMapping(value="/getAngular",method = {RequestMethod.GET})
	  public   String getUser2(@RequestParam Model model) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("emailId", "501258834@qq.com");
		map.put("userName", "yuansq");
		model.addAttribute("user",map );
	    return "kid/hello2";
	  }
	
}
