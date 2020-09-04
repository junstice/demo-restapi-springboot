package com.example.demo.dbsettingex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dbsettingex.dto.Member;
import com.example.demo.dbsettingex.service.MembersService;

@RestController
public class MembersController {

	@Autowired
	private MembersService service;
	
	@GetMapping(path = "/MemberList")
	public List<Member> memberList() {
		return service.memberList();
	}
}
