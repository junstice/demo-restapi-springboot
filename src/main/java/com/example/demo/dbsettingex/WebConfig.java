package com.example.demo.dbsettingex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
	
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
				
//				registry.addMapping("/members")
				registry.addMapping("/members/**") // 와일드카드 지정 가능
						.allowedOrigins("*")
						.allowedMethods(
								HttpMethod.GET.name(),
								HttpMethod.POST.name(),
								HttpMethod.PUT.name(),
								HttpMethod.DELETE.name(),
								HttpMethod.HEAD.name(),
								HttpMethod.PATCH.name()
								);
//						.allowedMethods("*"); // 모든 메소드를 허용 함
			}
		};
	}
}
