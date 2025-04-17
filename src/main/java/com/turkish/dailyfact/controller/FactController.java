package com.turkish.dailyfact.controller;

import com.turkish.dailyfact.model.Fact;
import com.turkish.dailyfact.service.FactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class FactController {

    private final FactService factService;
    private final LocalDate date = LocalDate.now();

    @GetMapping("/")
    public String showFactOfTheDay(Model model) {
        Fact fact = factService.getDailyFact();
        model.addAttribute("fact", fact);
        model.addAttribute("date", date);
        return "index"; // шаблон fact-of-the-day.html
    }

    @GetMapping("/random")
    public String showRandomFact(Model model) {
        Fact fact = factService.getRandomFact();
        model.addAttribute("fact", fact);
        model.addAttribute("date", date);
        return "index";
    }

}
