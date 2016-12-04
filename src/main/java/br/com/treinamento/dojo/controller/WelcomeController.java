package br.com.treinamento.dojo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> helloWorld() {
		
		String message = 
			"<br>" +
				"<div style='text-align:center;'>"
					+ "<h2>Spring MVC</h2>"
					+ "<h2>APIs Marvel</h2>"
					+ "<h3>Series</h3>"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/serie\">Serie</a>"
					+ "<br />"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/series\">Series</a>"
					+ "<br />"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/series/725\">Serie 725</a>"
					+ "<br />"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/series/725/characters\">Serie 725 characters</a>"
					+ "<br />"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/series/725/comics\">Serie 725 comics</a>"
					+ "<br />"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/series/725/creators\">Serie 725 creators</a>"
					+ "<br />"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/series/725/creators\">Serie 725 stories</a>"
					+ "<br />"
					+ "<br />"
					+ "<h3>Characters</h3>"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/characters\">Characters</a>"
					+ "<br />"
					+ "<br />"
					+ "<h3>Comics</h3>"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/v1/comics\">Comics</a>"
					+ "<br />"
					+ "<br />"
					+ "<h3>User</h3>"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/user/\">Users</a>"
					+ "<br />"
					+ "<br />"
					+ "<a href=\"/app-spring-ciet/user/1/\">User 1</a>"
					+ ""
				+ "</div>"
			+ "<br />"
			+ "<br />";
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}