package de.htw_berlin.wtprojekt;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @NotBlank(message = "Der Übungsname darf nicht leer sein")
    private String name;
    @Min(value = 1, message = "Es muss mindestens 1 Satz absolviert werden")
    private int sets;

    public Workout() {
        this.date = LocalDate.now();
    }

    public Workout(String name, int sets) {
        this.name = name;
        this.sets = sets;
        this.date = LocalDate.now();
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}