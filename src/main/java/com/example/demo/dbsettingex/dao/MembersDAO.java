package com.example.demo.dbsettingex.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dbsettingex.dto.Member;

@Repository
public interface MembersDAO {
	List<Member> selectMembers();
	void registerMember(Member member);
	Integer putMember(@Param("id") Integer id, @Param("member") Member member);
	Integer patchMember(@Param("id") Integer id, @Param("member") Member member);
	Integer removeMember(Integer id);
}
