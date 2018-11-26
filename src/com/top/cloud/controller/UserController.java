package com.top.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.top.cloud.bean.User;
import com.top.cloud.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/selectUserById")
	@ResponseBody()
	public ModelAndView selectUserById(int id){
		User user = userService.selectUserById(id);
		ModelAndView view = new ModelAndView();
		view.addObject("user", user);
		view.setViewName("index");
		return view;
	}

}
