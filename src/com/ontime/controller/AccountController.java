package com.ontime.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ontime.model.User;
import com.ontime.service.AccountService;
import com.ontime.util.LoginResult;
import com.ontime.viewmodel.LoginUserViewModel;

@Controller
public class AccountController {

	private AccountService service;

	public AccountController(AccountService service) {
		this.service = service;
	}

	@RequestMapping(value = "account/login", method = RequestMethod.GET)
	public String login(Model model) {
		LoginUserViewModel login = new LoginUserViewModel();
		model.addAttribute("login", login);
		return "account/login";
	}

	@RequestMapping(value = "account/login", method = RequestMethod.POST)
	public String login(@Valid LoginUserViewModel login, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "account/login";
		}
		
		LoginResult loginResult = service.login(login.getEmail(), login.getPassword());

		if (loginResult != LoginResult.SUCCESS) {
			String message = "Tente novamente mais tarde.";
			if (loginResult == LoginResult.NOT_FOUND) {
				message = "O e-mail inserido não corresponde a nenhuma conta. Cadastre-se para abrir uma conta.";
			} else if (loginResult == LoginResult.INVALID) {
				message = "A senha inserida está incorreta.";
			}
			model.addAttribute("message", message);
			model.addAttribute("login", login);
			return "account/login";
		}

		return "redirect:/";
	}

	@RequestMapping(value = "account/register", method = RequestMethod.GET)
	public String register(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "account/register";
	}

	@RequestMapping(value = "account/register", method = RequestMethod.POST)
	public String register(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "account/register"; 
		}
		
		Boolean registerSucceeded = service.register(user);

		if (!registerSucceeded) {
			model.addAttribute("message", "Tente novamente mais tarde.");
			return "account/register";
		}

		return "redirect:/account/login";
	}

	@RequestMapping(value = "account/logout", method = RequestMethod.GET)
	public String logout() {
		service.logout();
		return "redirect:/";
	}

}
