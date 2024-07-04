package com.panda.demo.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.panda.demo.feignClient.response.SunatResponse;

@FeignClient(name = "sunat-client", url = "https://api.apis.net.pe/v2/sunat/")
public interface SunatClient {
	@GetMapping("/ruc")
    SunatResponse getInfo(@RequestParam("numero") String numero,
                          @RequestHeader("Authorization")String token);
    //dni?numero=73005607
}
