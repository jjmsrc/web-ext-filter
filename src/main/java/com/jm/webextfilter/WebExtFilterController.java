package com.jm.webextfilter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebExtFilterController {

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

}
