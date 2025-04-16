package com.turkish.dailyfact.controller;

import com.turkish.dailyfact.model.Fact;
import com.turkish.dailyfact.service.FactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fact")
@RequiredArgsConstructor
public class FactController {

    private final FactService factService;

    @GetMapping("/random")
    public Fact getRandomFact() {
        return factService.getRandomFact();
    }

    @GetMapping("/today")
    public Fact getDailyFact() {
        return factService.getDailyFact();
    }

    @PostMapping("/add")
    public void addFact(@RequestBody Fact fact) {
        factService.setFact(fact);
    }

}
