package com.example.demo.dbsettingex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dbsettingex.dao.MembersDAO;
import com.example.demo.dbsettingex.dto.Member;
import com.example.demo.dbsettingex.service.MembersService;

@Service
public class MemberServiceImpl implements MembersService {
	
	@Autowired
	private MembersDAO dao;
	
	@Override
	public Member test() {

		return null;
	}

	@Override
	public List<Member> memberList() {		
		return dao.selectMembers();
	}

	@Override
	public void registerMember(Member member) {
		dao.registerMember(member);
	}
}
