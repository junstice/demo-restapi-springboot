package com.example.demo.dbsettingex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dbsettingex.dto.Member;
import com.example.demo.dbsettingex.service.MembersService;

/* 
 * HttpResponse를 JSON으로 자동 직렬화하는 기능을 가진 @ResponseBody가  
 * @RestController에 포함되기 때문에 @ResponseBody를 각 컨트롤러 메소드에 추가할 필요가 없다.
 * */
@RestController
@RequestMapping(path = "/members")
//@CrossOrigin(origins = "http://localhost:8080") // 컨트롤러 레벨 혹은 메소드 레벨에서 CORS 허용할 때 사용
public class MembersController {

	@Autowired
	private MembersService service;
	
//	@CrossOrigin(origins = "http://localhost:8080")
//	@GetMapping(path = "/test")
//	public Member testMember() {
//		Member test = new Member();
//		test.setId(1);
//		test.setName("testName");
//		test.setAge(10);
//		test.setDept("test");
//		return test;
//	}
	
	@GetMapping//(path = "/members")
	public List<Member> memberList() {
		return service.memberList();
	}
	
	@PostMapping//(path = "/members")
	public Member registerMember(@RequestBody Member member) { // @RequestBody: HttpRequsetBody를 자동적으로 JavaObject로 변환해 줌
		service.registerMember(member);
		return member;
	}
	
	@PutMapping(path = "/{id}") // 전체 갱신(CREATE/REPLACE)
	public Member putMember(@PathVariable Integer id, @RequestBody Member member) {
		
		return service.putMember(id, member);
	}

	@DeleteMapping(path = "/{id}")
	public Integer removeMember(@PathVariable Integer id) {
		return service.removeMember(id);
	}

	@PatchMapping(path = "/{id}") // 일부 갱신
	public Member patchMember(@PathVariable Integer id, @RequestBody Member member) {
		
		return service.patchMember(id, member);
	}
}
