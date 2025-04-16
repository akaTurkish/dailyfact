package com.turkish.dailyfact.repository;

import com.turkish.dailyfact.model.Fact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FactRepository extends JpaRepository<Fact, Long> {

    @Query(value = "SELECT * FROM FACTS ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Fact getRandomFact();

    @Query("SELECT MAX(f.publishDate) FROM Fact f")
    Optional<LocalDate> findMaxPublishDate();

    Fact getFactByPublishDate(LocalDate published);
}