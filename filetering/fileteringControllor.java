package com.aci.payon.rest.aprosewebservices.restfulwebservices.filetering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class fileteringControllor {
	
	@GetMapping(path ="/filetering")
	public SomeBean retriveBean() {
		
		return new SomeBean("Val1", "val2", "Val3");
	}

}
