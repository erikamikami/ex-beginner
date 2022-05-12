package com.example.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;

@Controller
@RequestMapping("/exam06")
public class ShoppingCartController {

	@Autowired
	private ServletContext application;

	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String index(Model model) {
		// 商品一覧を作成し、applicationスコープに入れる
		List<Item> itemList = new LinkedList<Item>() {
			{
				add(new Item("手帳ノート", 1000));
				add(new Item("文房具セット", 1500));
				add(new Item("ファイル", 2000));
			}
		};
		application.setAttribute("itemList", itemList);


		// sessionスコープ内に空の商品⼀覧を入れる
		if (session.getAttribute("cartList") == null) {
			List<Item> cartList = new LinkedList<>();
			session.setAttribute("cartList", cartList);
		}

		// requestスコープに合計金額を格納
		Integer totalAmount = 0;
		List<Item> cartList = (List<Item>) session.getAttribute("cartList");
		for (Item item : cartList) {
			totalAmount += item.getPrice();
		}
		model.addAttribute("totalAmount", totalAmount);

		return "item-and-cart";
	}

	// カートに追加
	@RequestMapping("/incart")
	public String inCart(int index, Model model) {
		// 送られてきたindex番号を基にapplicationスコープからその商品を取得
		LinkedList<Item> itemList = (LinkedList<Item>) application.getAttribute("itemList");
		Item newItem = itemList.get(index - 1);

		// ショッピングカート(sessionスコープ内のリスト)に入れる
		List<Item> cartList = (List<Item>) session.getAttribute("cartList");
		cartList.add(newItem);
		session.setAttribute("cartList", cartList);

		return "redirect:/exam06";
	}

	// カートから削除
	@RequestMapping("/delete")
	public String delete(int index, Model model) {
		// 送られてきたindex番号を基にsessionスコープからその商品を削除する
		List<Item> cartList = (List<Item>) session.getAttribute("cartList");
		cartList.remove(index - 1);
		session.setAttribute("cartList", cartList);

		return "redirect:/exam06";
	}

}
