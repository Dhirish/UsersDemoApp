package com.appsdeveloperblog.photoapp.api.account.ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountContoller {
	
	@GetMapping("/status/check")
	public String Status() {
		return "Account page";
	}

}
