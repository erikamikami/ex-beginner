package com.example.controller;

import java.text.NumberFormat;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping("")
	public String index() {
		return "exam03";
	}
	
	@RequestMapping("/result")
	public String result(int item1, int item2, int item3) {
		NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式
		
		// 税抜き価格
		int noTaxResult = item1 + item2 + item3;
		String formatNoTaxResult = nfNum.format(noTaxResult);
		
		// 税込み価格(税率：10%）
		int taxResult = (int) (noTaxResult * 1.1);
		String formatTaxResult = nfNum.format(taxResult);
		
		application.setAttribute("formatNoTaxResult", formatNoTaxResult);
		application.setAttribute("formatTaxResult", formatTaxResult);
		
		return "exam03-result";
	}
}
