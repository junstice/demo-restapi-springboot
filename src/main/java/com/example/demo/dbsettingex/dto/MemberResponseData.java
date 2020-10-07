package com.example.demo.dbsettingex.dto;

import java.util.List;

import lombok.Data;

@Data
public class MemberResponseData {
	private int statuscode;
	private Member parameterInfo;
	private int resultCount;
	private List<Member> resultDataList;
}
