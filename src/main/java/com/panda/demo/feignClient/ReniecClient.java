package com.panda.demo.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.panda.demo.feignClient.response.ReniecResponse;

@FeignClient(name = "reniec-client", url = "https://api.apis.net.pe/v2/reniec/")
public interface ReniecClient {
	@GetMapping("/dni")
    ReniecResponse getInfo(@RequestParam("numero") String numero,
                           @RequestHeader("Authorization") String token);
	
}
