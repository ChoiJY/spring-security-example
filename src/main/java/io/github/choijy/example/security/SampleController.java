package io.github.choijy.example.security;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description : Sample Controller
 *
 * Created by jychoi on 2022/06/26.
 */
@Controller
public class SampleController {

	@GetMapping("/")
	public String index(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("message", "hello");
		} else {
			model.addAttribute("message", "hello anonymous");
		}
		return "index";
	}

	@GetMapping("/info")
	public String info() {
		return "info";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

}
