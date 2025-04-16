package com.turkish.dailyfact.service.impl;

import com.turkish.dailyfact.model.Fact;
import com.turkish.dailyfact.repository.FactRepository;
import com.turkish.dailyfact.service.FactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FactServiceImpl implements FactService {

    public final FactRepository factRepository;

    @Override
    public Fact getRandomFact() {
        return factRepository.getRandomFact();
    }

    @Override
    public Fact getDailyFact() {
        return factRepository.getFactByPublishDate(LocalDate.now());
    }

    @Override
    public void setFact(Fact fact) {
        LocalDate latestDate = factRepository.findMaxPublishDate().orElse(LocalDate.now().minusDays(1));
        fact.setPublishDate(latestDate.plusDays(1));
        factRepository.save(fact);

    }

}
