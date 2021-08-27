package com.aci.payon.rest.aprosewebservices.restfulwebservices.HelloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping (path = "/hello_world")
	public String hellowWorld() {
		return "Aprose World";
	}
	
	@GetMapping (path = "/hello_world_bean")
	public HellowWorldBean hellowWorldBean() {
		return new HellowWorldBean("Aprose World");
	}
	
	@GetMapping (path = "/hello_world_bean/{name}")
	public HellowWorldBean hellowWorldBeanPathVariable(@PathVariable String name) {
		return new HellowWorldBean(String.format("%s's , World" , name));
	}

	@GetMapping (path = "/hello_world_bean_internationalization")
	public String hellowWorldBeanInternationalization() {
		return messageSource.getMessage("good-morning-message", null, LocaleContextHolder.getLocale());
	}
}
