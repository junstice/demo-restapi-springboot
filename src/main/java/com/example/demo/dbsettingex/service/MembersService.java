package com.example.demo.dbsettingex.service;

import java.util.List;

import com.example.demo.dbsettingex.dto.Member;

public interface MembersService {
	Member test();
	List<Member> memberList();
	void registerMember(Member member);
	Member putMember(Integer id, Member member);
	Member patchMember(Integer id, Member member);
	Integer removeMember(Integer id);
}
