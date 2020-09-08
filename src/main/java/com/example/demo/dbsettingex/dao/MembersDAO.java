package com.example.demo.dbsettingex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dbsettingex.dto.Member;

@Repository
public interface MembersDAO {
	List<Member> selectMembers();
	void registerMember(Member member);
}
