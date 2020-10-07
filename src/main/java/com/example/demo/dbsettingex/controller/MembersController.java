package com.example.demo.dbsettingex.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.example.demo.dbsettingex.dto.MemberResponseData;
import com.example.demo.dbsettingex.dto.MemberXml;
import com.example.demo.dbsettingex.dto.MemberXmlList;
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
	
	@GetMapping("/{id}")
	public Member getMember(@PathVariable Integer id) {
		return service.getMember(id);
	}
	
	@GetMapping//(path = "/members")
	public List<Member> memberList() {
		return service.memberList();
	}
	
	@PostMapping//(path = "/members")
	public Member registerMember(@RequestBody Member member) { // @RequestBody: HttpRequsetBody를 자동적으로 JavaObject로 변환해 줌
		service.registerMember(member);
		return member;
	}
	
	@PutMapping(path = "/{id}") // 전체 갱신(CREATE/REPLACE), REST 규칙에 따라 자원을 PATH로 받고 수정될 내용은 requestbody로부터 받는다.
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
	
	////////////////////////////// 여기부터는 마이바티스 검증용 메소드
	@PostMapping("/mpost")
	public Integer multipleInsert(@RequestBody List<Member> members) {
		return service.multipleInsert(members); // 다중 insert: 성공한 개수를 리턴
	}
	
	@PutMapping("/mput")
	public Integer multipleUpdate() {
		return service.multipleUpdate(); // 다중 update: 성공한 개수를 리턴
	}
	
	@DeleteMapping("/mdelete")
	public Integer multipleDelete() {
		return service.multipleDelete(); // 다중 update: 성공한 개수를 리턴
	}
	

	/***************************************************************************************
	 * 이 곳부터는 넥사크로 DataObject 검증을 위한 샘플 메소드
	 ***************************************************************************************/	

	/**
	 * GET 요청 처리
	 * @param member (JSON 필수 아님) 
	 * @param param  (String 필수 아님)
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@GetMapping("/nxtest")
	public MemberResponseData nx_getAllMembers(@RequestBody(required = false) Member member, 
												@RequestParam(required = false) String param) {
		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	}
	
	/**
	 * POST 요청 처리 (파라미터가 JSON 타입)
	 * @param member 
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@PostMapping(path="/nxtest", consumes = "application/json")
	public MemberResponseData nx_insertMember(@RequestBody Member member) {
		
		service.registerMember(member);
		
		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	}
	
	/**
	 * POST 요청 처리 (파라미터가 텍스트 타입)
	 * @param params(key-value 세트)
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@PostMapping(path = "/nxtest", consumes = "application/x-www-form-urlencoded")
//	public MemberResponseData nx_insertMember(@RequestParam String name, @RequestParam Integer age, @RequestParam String dept) {
	public MemberResponseData nx_insertMember(@RequestParam Map<String, String> params) {

		Member member = new Member();
		member.setName(params.get("name"));
		member.setAge(Integer.parseInt(params.get("age")));
		member.setDept(params.get("dept"));

		service.registerMember(member);
		
		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	}

	/**
	 * PUT 요청 처리 (파라미터가 JSON 타입)
	 * @param id 
	 * @param member 
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@PutMapping(path = "/nxtest/{id}", consumes = "application/json")
	public MemberResponseData nx_updateMemberJSON(@PathVariable Integer id, @RequestBody Member member) {

		service.putMember(id, member);
		
		member.setId(id);

		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	}

	/**
	 * PUT 요청 처리 (파라미터가 텍스트 타입)
	 * @param id 
	 * @param params(key-value 세트)
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@PutMapping(path = "/nxtest/{id}", consumes = "application/x-www-form-urlencoded")
	public MemberResponseData nx_updateMemberFORM(@PathVariable Integer id, @RequestParam Map<String, String> params) {

		Member member = new Member();
		member.setId(id);
		member.setName(params.get("name"));
		member.setAge(Integer.parseInt(params.get("age")));
		member.setDept(params.get("dept"));

		service.putMember(id, member);		
		
		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	}

	/**
	 * PATCH 요청 처리 (파라미터가 JSON 타입)
	 * @param id 
	 * @param member 
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@PatchMapping(path = "/nxtest/{id}", consumes = "application/json")
	public MemberResponseData nx_patchMemberJSON(@PathVariable Integer id, @RequestBody Member member) {

		service.patchMember(id, member);
		
		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	}

	/**
	 * PATCH 요청 처리 (파라미터가 텍스트 타입)
	 * @param id 
	 * @param params(key-value 세트)
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@PatchMapping(path = "/nxtest/{id}", consumes = "application/x-www-form-urlencoded")
	public MemberResponseData nx_patchMemberFORM(@PathVariable Integer id, @RequestParam Map<String, String> params) {

		Member member = new Member();
		member.setId(id);
		member.setName(params.get("name"));
		member.setAge(Integer.parseInt(params.get("age")));
		member.setDept(params.get("dept"));

		service.patchMember(id, member);
		
		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	}

	/**
	 * DELETE 요청 처리
	 * @param id 
	 * @return MemberResponseData statuscode, 파라미터 정보, 처리 후 모든 결과 리스트
	 */
	@DeleteMapping("/nxtest/{id}")
	public MemberResponseData nx_deleteMember(@PathVariable Integer id) {
		
		service.removeMember(id);
		
		Member member = new Member();
		member.setId(id);
		
		// 결과 작성
		MemberResponseData res = new MemberResponseData();
		res.setStatuscode(200);
		res.setParameterInfo(member);
		res.setResultCount(service.memberList().size());
		res.setResultDataList(service.memberList());
		return res;
	} 

	////////////////////////////////////////////////////////////////////// ↓↓↓ XML response 
	@GetMapping(path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public MemberXmlList getAllMembersXML() {
		List<MemberXml> list = new ArrayList<MemberXml>();
		list.add(new MemberXml(1, "AAA", 22, "it"));
		list.add(new MemberXml(2, "BBB", 24, "ia"));
		list.add(new MemberXml(3, "CCC", 25, "ix"));
		list.add(new MemberXml(4, "DDD", 26, "iv"));
		
		MemberXmlList res = new MemberXmlList();
		res.setMembers(list);
		
		return res;
	}
}
