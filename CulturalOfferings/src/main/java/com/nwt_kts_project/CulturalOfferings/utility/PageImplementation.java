package com.nwt_kts_project.CulturalOfferings.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class PageImplementation<T> extends PageImpl<T> {
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PageImplementation(@JsonProperty("content") List<T> content,
			@JsonProperty("number") int number,
			@JsonProperty("size") int size,
			@JsonProperty("totalElements") int totalElements,
			@JsonProperty("totalPages") int totalPages,
			@JsonProperty("numberOfElements") int numberOfElements,
			@JsonProperty("first") boolean first,
			@JsonProperty("last") boolean last,
			@JsonProperty("pageable") JsonNode pageable,
			@JsonProperty("sort") JsonNode sort) {
		
		super(content, PageRequest.of(number,size), totalElements);
		
	}
	
	public PageImplementation(List<T> content,Pageable pageable, long total) {
		super(content,pageable,total);
	}
	public PageImplementation(List<T> content) {
		super(content);
	}
	
	public PageImplementation() {
		super(new ArrayList<>());
	}
}
