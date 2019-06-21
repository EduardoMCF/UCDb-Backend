package com.psoft.UCDb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
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
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("UCDb\\src\\main\\resources\\subject.json"));
        HashMap<Integer, String> disciplinas = gson.fromJson(bufferedReader, HashMap.class);
        for (String name : disciplinas.values()) {
        	System.out.println(name);
		}
	}

}
