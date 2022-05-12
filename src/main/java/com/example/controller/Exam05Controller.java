package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Member;
import com.example.repository.MemberRepository;

@Controller
@RequestMapping("/exam05")
public class Exam05Controller {

	@Autowired
	MemberRepository repository;

	@RequestMapping("")
	public String index() {
		return "exam05";
	}

	@RequestMapping("/result")
	public String result(String partOfName, Model model) {
		List<Member> members = new ArrayList<>();

		members = repository.selectLikeName(partOfName);

		if (members.size() == 0) {
			model.addAttribute("noResult", "※ヒットは0件でした。");
			return index();
		} else {
			model.addAttribute("members", members);
			for (Member x : members) {
				System.out.println(x);
			}
			return "exam05-result";
		}

	}

}
