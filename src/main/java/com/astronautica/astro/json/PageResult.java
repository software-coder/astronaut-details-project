package com.astronautica.astro.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerializableSchema
@JsonIgnoreProperties(ignoreUnknown=true)
public class PageResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer count;
	
	private String next;
	
	private String previous;
	
	private List<AstronautJson> results;
}
