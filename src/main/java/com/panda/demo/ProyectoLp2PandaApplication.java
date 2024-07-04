package com.panda.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients("com.panda.demo") //habilidamos el feignclients
@ImportAutoConfiguration({FeignAutoConfiguration.class}) //importe la autoconfiguracion necesaria para utilizar feign
public class ProyectoLp2PandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoLp2PandaApplication.class, args);
	}

}
