package de.htw_berlin.wtprojekt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkoutController {

    @GetMapping("/")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/workouts")
    public List<Workout> getAllWorkouts() {
        return List.of(
                new Workout("Dips", 3),
                new Workout("Liegestütze", 4),
                new Workout("Bizeps-Curls", 3),
                new Workout("Klimmzüge", 3)
        );
    }
}
