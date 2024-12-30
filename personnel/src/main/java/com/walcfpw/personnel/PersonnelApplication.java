package com.walcfpw.personnel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PersonnelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelApplication.class, args);
	}

//	https://stackoverflow.com/a/59610519
}
