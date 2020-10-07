package com.example.demo.dbsettingex.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class MemberXmlList {
	
	@JacksonXmlProperty(localName = "xmembers")
	@JacksonXmlElementWrapper(useWrapping = false)
	List<MemberXml> members = new ArrayList<>();
}
