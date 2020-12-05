package com.sept.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
//
public class HelloWorldController {

	@GetMapping(path = "/hellow-wrold")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	///hello-world/path-variable/sept
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		//throw new RuntimeException("Something went wrong");
		return new HelloWorldBean(String.format("Welcome back, %s. Thanks for logging in", name));
	}

	@RequestMapping("/")
	public String home() {
	  return "Hello World!";
	}
  
	/**
	 * (Optional) App Engine health check endpoint mapping.
	 * @see <a href="https://cloud.google.com/appengine/docs/flexible/java/how-instances-are-managed#health_checking"></a>
	 * If your app does not handle health checks, a HTTP 404 response is interpreted
	 *     as a successful reply.
	 */
	@RequestMapping("/_ah/health")
	public String healthy() {
	  // Message body required though ignored
	  return "Still surviving.";
	}
}
