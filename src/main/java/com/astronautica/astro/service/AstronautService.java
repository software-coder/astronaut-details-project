package com.astronautica.astro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.astronautica.astro.json.AstronautJson;
import com.astronautica.astro.json.PageResult;

import reactor.core.publisher.Mono;

@Service
public class AstronautService {

	// @formatter:off
	
	private static final String URL_ASTRONAUTS = "https://spacelaunchnow.me/api/3.5.0/astronaut/";
	private final Integer PAGE_SIZE = 100;
	
	public Mono<List<AstronautJson>> getAstronauts() {
		return this.hitApi(URL_ASTRONAUTS + "?format=json&limit=" + String.valueOf(PAGE_SIZE), null, new ArrayList<>())
				.map(result-> result.getV());
	}
	
	public Mono<Pair<PageResult, List<AstronautJson>>> hitApi(String url, PageResult page, List<AstronautJson> list){
		
		if (page != null && !page.getResults().isEmpty()) {
			list.addAll(page.getResults());
		}
		
		ParameterizedTypeReference<PageResult> typeRef = 
				new ParameterizedTypeReference<PageResult>() {};
				
		WebClient client;
		
		if (url != null) {
			client = WebClient.builder()
				.baseUrl(url)
				.build();
		} else if (page.getNext() != null) {
			client = WebClient.builder()
					.baseUrl((page.getNext() + "&format=json").replace("http", "https"))
					.build();
		} else {
			return Mono.just(new Pair<>(page, list));
		}
		 
		return client.get()
			.accept(MediaType.ALL)
			.retrieve()
			.bodyToMono(typeRef)
			.flatMap(result -> this.hitApi(null, result, list));
	}

	public Mono<AstronautJson> getAstronaut(Long id) {
		WebClient client = WebClient
				.builder()
				.baseUrl(URL_ASTRONAUTS + id + "/?format=json")
				.build();
		
		ParameterizedTypeReference<AstronautJson> typeRef
			= new ParameterizedTypeReference<AstronautJson>() {};
		
		return client.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(typeRef);
	}
	
	// @formatter:on
}
