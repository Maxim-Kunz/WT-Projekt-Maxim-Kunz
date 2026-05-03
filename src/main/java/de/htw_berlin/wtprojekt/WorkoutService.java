package de.htw_berlin.wtprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository repository;

    public List<Workout> getAllWorkouts() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(Workout::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Workout save(Workout workout) {
        return repository.save(workout);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}