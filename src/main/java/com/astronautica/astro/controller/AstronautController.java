package com.astronautica.astro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astronautica.astro.json.AstronautJson;
import com.astronautica.astro.service.AstronautService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
@Api("Astronaut API")
@RequestMapping("/astronauts/1.0")
public class AstronautController {

	// @formatter:off
	@Autowired
	private AstronautService service;
		
	@CrossOrigin
	@ApiOperation(value = "", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Ok", response = AstronautJson.class),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ResponseEntity<List<AstronautJson>>> getAstronauts() {
		return service.getAstronauts()
					.map(data-> ResponseEntity
						.ok()
						.body(data));
	}
	
	@ApiOperation(value = "/astronaut/{id}", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Ok", response = AstronautJson.class),
	@ApiResponse(code = 400, message = "Bad Request", response = String.class),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/astronaut/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ResponseEntity<AstronautJson>> getAstronaut(
		@ApiParam(value="An integer value") @PathVariable("id") final Long id) {
		return service.getAstronaut(id)
					.map(data-> ResponseEntity
						.ok()
						.body(data));
	}
	// @formatter:on
}
