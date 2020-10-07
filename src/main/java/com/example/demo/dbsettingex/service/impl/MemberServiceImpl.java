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
	public Member getMember(Integer id) {
		return dao.getMember(id);
	}

	@Override
	public List<Member> memberList() {		
		return dao.selectMembers();
	}

	@Override
	public void registerMember(Member member) {
		dao.registerMember(member);
	}

	@Override
	public Member putMember(Integer id, Member member) {

		Member putMember = new Member();
		
		// UPDATE에 성공했다면 갱신된 member를 반환
		if (dao.putMember(id, member) == 1) {
			putMember.setId(id);
			putMember.setName(member.getName());
			putMember.setAge(member.getAge());
			putMember.setDept(member.getDept());
		}
		
		return putMember;
	}

	@Override
	public Member patchMember(Integer id, Member member) {
		
		Member patchMember = new Member();
		
		// UPDATE에 성공했다면 갱신된 member를 반환
		if (dao.patchMember(id, member) == 1) {
			patchMember.setId(id);
			
			if (member.getName() != null) patchMember.setName(member.getName());  
			if (member.getAge() != null) patchMember.setAge(member.getAge());  
			if (member.getDept() != null) patchMember.setDept(member.getDept());
		}
		
		return patchMember;
	}

	@Override
	public Integer removeMember(Integer id) {
		return dao.removeMember(id);
	}

	@Override
	public Integer multipleInsert(List<Member> members) {
		return dao.multipleInsert(members);
	}

	@Override
	public Integer multipleUpdate() {
		return dao.multipleUpdate();
	}

	@Override
	public Integer multipleDelete() {
		return dao.multipleDelete();
	}
}
