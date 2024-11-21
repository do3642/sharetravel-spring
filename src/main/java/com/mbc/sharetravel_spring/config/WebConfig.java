package com.mbc.sharetravel_spring.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //허용할 주소 (내 주소중에)
		.allowedOrigins("http://localhost:3000") //누구한테 허용할건지 (요청보내오는?보내고있는주소)
		.allowedMethods("GET","POST","PUT","DELETE");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/travel_img/**")
	.addResourceLocations("file:travel_img/");
	}

}
