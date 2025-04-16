package com.turkish.dailyfact.service;

import com.turkish.dailyfact.model.Fact;

public interface FactService {
    Fact getRandomFact();
    Fact getDailyFact();
    void setFact(Fact fact);
}
