package ru.project.starter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class HomeControllerV1 {
    @GetMapping("/")
    public String base(@AuthenticationPrincipal UserDetails userDetails) {
        return "welcome";
    }
}
