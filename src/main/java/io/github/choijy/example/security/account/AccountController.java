package io.github.choijy.example.security.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Description : Account Sample Controller class.
 *
 * Created by jychoi on 2022/06/27.
 */
@RequiredArgsConstructor
@RestController
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/account/{role}/{username}/{password}")
	public Account createAccount(@ModelAttribute Account account) {
		return accountService.createUser(account);
	}
}
