package com.javaexpress.accounts.services.clients;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaexpress.accounts.dto.CardDTO;

@FeignClient(name = "CARDS", fallback = CardFallBack.class)
@LoadBalancerClient(name = "CARDS")
public interface CardFeignClient {
	
	@GetMapping("cards/fetchAll")
	public List<CardDTO> getAllCards(@RequestParam String mobileNumber);
	
	@PutMapping("cards/deactivateAll")
	public void deactivateAllCards(@RequestParam String mobileNumber);
	
}
