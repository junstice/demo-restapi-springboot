package com.example.demo.dbsettingex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dbsettingex.dto.Member;
import com.example.demo.dbsettingex.service.MembersService;

@RestController
public class MembersController {

	@Autowired
	private MembersService service;
	
	@GetMapping(path = "/test")
	public Member testMember() {
		Member test = new Member();
		test.setId(1);
		test.setName("testName");
		test.setAge(10);
		test.setDept("test");
		return test;
	}
	
	@GetMapping(path = "/members")
	public List<Member> memberList() {
		return service.memberList();
	}
	
	@PostMapping(path = "/members")
	public Member registerMember(@RequestBody Member member) {
		service.registerMember(member);
		return member;
	}
}
