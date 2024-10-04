package ru.vzaytsev.studythumeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class HomeControllerV1 {
    @PostMapping("/studythymeleaf/sso")
    public String handleSSOLogin(@AuthenticationPrincipal UserDetails userDetails) {
        return "redirect:/";
    }

    @GetMapping("/")
    public String base(@AuthenticationPrincipal UserDetails userDetails) {
        return "index";
    }
}
