package com.aci.payon.rest.aprosewebservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionControllor {
	
	@GetMapping (path = "/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Aprose Shaikh");
	}


	@GetMapping (path = "/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Aprose", "Shaikh"));
	}


	@GetMapping (path = "/person/param", params ="version=1")
	public PersonV1 getParamV1() {
		return new PersonV1("Aprose Shaikh");
	}


	@GetMapping (path = "/person/param", params ="version=2")
	public PersonV2 getParamV2() {
		return new PersonV2(new Name("Aprose", "Shaikh"));
	}
	
	@GetMapping (path = "/person/header", headers ="X_API_VERSION=1")
	public PersonV1 getParamHeaderV1() {
		return new PersonV1("Aprose Shaikh");
	}


	@GetMapping (path = "/person/header", headers ="X_API_VERSION=2")
	public PersonV2 getParamHeaderV2() {
		return new PersonV2(new Name("Aprose", "Shaikh"));
	}
	
	@GetMapping (path = "/person/produces", produces ="application/vnd.company.app.v1+json")
	public PersonV1 getParamProducesV1() {
		return new PersonV1("Aprose Shaikh");
	}


	@GetMapping (path = "/person/produces", produces ="application/vnd.company.app.v2+json")
	public PersonV2 getParamProducesV2() {
		return new PersonV2(new Name("Aprose", "Shaikh"));
	}

}
