package com.javaexpress.accounts.services.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaexpress.accounts.dto.LoanDTO;


@FeignClient(name = "LOANS", fallback = LoanFallBack.class)
@LoadBalancerClient(name = "LOANS")
public interface LoanFeignClient {
	 @GetMapping("loans/fetch")
	    public LoanDTO fetchLoansDetails(@RequestParam String mobileNumber);
}
