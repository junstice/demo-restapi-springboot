package com.example.demo.dbsettingex.dao;

import java.util.List;

import com.example.demo.dbsettingex.dto.Member;

public interface MembersDAO {
	List<Member> selectMembers();
}
