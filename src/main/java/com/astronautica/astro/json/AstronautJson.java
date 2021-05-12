package com.astronautica.astro.json;

import java.io.Serializable;

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
public class AstronautJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String url;
	
	private String name;
	
	private String nationality;
	
	private String profile_image_thumbnail;
	
	private String date_of_birth;
	
	private String bio;
}
