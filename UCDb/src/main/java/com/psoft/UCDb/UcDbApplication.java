package com.psoft.UCDb;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.psoft.UCDb.rest.filters.TokenFilter;

@SpringBootApplication
public class UcDbApplication {
	
	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());
		filterRb.addUrlPatterns("/teste");
		return filterRb;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(UcDbApplication.class, args);
	}
}
