package com.turkish.dailyfact.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FACTS")
public class Fact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FACT", nullable = false)
    @NotBlank
    private String fact;

    @Column(name = "DESCRIPTION", nullable = false)
    @Size(min = 20, max = 250)
    private String description;

    @Column(name = "PUBLISH")
    private LocalDate publishDate;

    public Fact(String fact, String description) {
        this.fact = fact;
        this.description = description;
    }

}