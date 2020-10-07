package com.example.demo.dbsettingex.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "xmember")
public class MemberXml {
	@JacksonXmlProperty(isAttribute = true) private Integer id;
	@JacksonXmlProperty private String name;
	@JacksonXmlProperty private Integer age;
	@JacksonXmlProperty private String dept;
}
