package com.aci.payon.rest.aprosewebservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import  org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver sessionLocalResolver = new AcceptHeaderLocaleResolver();
		sessionLocalResolver.setDefaultLocale(Locale.US);
		return sessionLocalResolver;
	}
	
	/*
	 * @Bean public ResourceBundleMessageSource messageSource() {
	 * ResourceBundleMessageSource resourceBundleMessageSource = new
	 * ResourceBundleMessageSource();
	 * resourceBundleMessageSource.setBasename("messages"); return
	 * resourceBundleMessageSource; }
	 */
}
