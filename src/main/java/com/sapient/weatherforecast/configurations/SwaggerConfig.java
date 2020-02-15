package com.sapient.weatherforecast.configurations;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.sapient.weatherforecast.controllers"))
	      .paths(PathSelectors.ant("/forecast/*"))
	      .build()
	      .apiInfo(apiInfo());
	}
	 
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Weather Forecast APP REST API", 
	      "Weather Forecast APP REST API", 
	      "V1", 
	      "Terms of service", 
	      new Contact("Arunachalam", "www.Sapient.com", "email@sapient.com"), 
	      "License of API", "API license URL");
	}
  @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
