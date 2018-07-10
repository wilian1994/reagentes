package br.com.reagentes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "reagentes/login";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "reagentes/login";
	}

}
