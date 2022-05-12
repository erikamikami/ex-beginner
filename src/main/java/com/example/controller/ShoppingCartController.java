package com.example.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;

@Controller
@RequestMapping("/exam06")
public class ShoppingCartController {

	@Autowired
	private ServletContext application;

	@RequestMapping("")
	public String index() {
		// 商品一覧を作成し、applicationスコープに入れる
		List<Item> itemList = new LinkedList<Item>() {
			{
				add(new Item("手帳ノート", 1000));
				add(new Item("文房具セット", 1500));
				add(new Item("ファイル", 2000));
			}
		};
		application.setAttribute("itemList", itemList);

		return "item-and-cart";
	}

}
