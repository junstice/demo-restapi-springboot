package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	/*
	 * CORS 허용을 전역으로 설정할 경우 작성한다.(혹은 config 파일로 작성)
	 * 
	 *  allowedMethods를 이용하여 허용할 메소드를 지정한다.
	 * 미지정 시, default로 GET, HEAD, POST만이 허용된다.
	 * */	
	@Bean
	public WebMvcConfigurer corsconfConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				// WebMvcConfigurer.super.addCorsMappings(registry);
				
				registry.addMapping("/members")
//						.addMapping("/members/**") // 와일드카드 지정 가능
						.allowedOrigins("http://localhost:8080")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
//						.allowedMethods("*"); // 모든 메소드를 허용 함
			}
		};
	}
}
