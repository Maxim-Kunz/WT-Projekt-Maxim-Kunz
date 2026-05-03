package de.htw_berlin.wtprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository repository;

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout) {
        return repository.save(workout);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        repository.deleteById(id);
    }
}