package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam02")
public class Exam02Controller {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("")
	public String index() {
		return "exam02";
	}
	
	@RequestMapping("/result")
	public String result(int firstFigure, int secondFigure) {
		session.setAttribute("firstFigure", firstFigure);
		session.setAttribute("secondFigure", secondFigure);
		int result = firstFigure + secondFigure;
		session.setAttribute("result", result);
		return "exam02-result";
	}
	
	@RequestMapping("/result2")
	public String result2() {
		return "exam02-result2";
	}
}
