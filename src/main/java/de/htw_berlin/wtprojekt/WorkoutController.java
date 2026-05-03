package de.htw_berlin.wtprojekt;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService service;

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return service.getAllWorkouts();
    }

    @PostMapping
    public Workout createWorkout(@Valid @RequestBody Workout workout) {
        return service.save(workout);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        service.delete(id);
    }
}