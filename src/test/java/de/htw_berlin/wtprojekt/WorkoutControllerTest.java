package de.htw_berlin.wtprojekt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutController.class)
class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WorkoutService service;

    @Test
    void shouldReturnWorkoutsList() throws Exception {
        Workout testWorkout = new Workout("Klimmzüge", 3);
        when(service.getAllWorkouts()).thenReturn(List.of(testWorkout));
        mockMvc.perform(get("/workouts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Klimmzüge")));
    }
    @Test
    void shouldCreateWorkout() throws Exception {
        Workout savedWorkout = new Workout("Liegestütze", 4);
        when(service.save(org.mockito.ArgumentMatchers.any(Workout.class))).thenReturn(savedWorkout);
        String jsonPayload = "{\"name\":\"Liegestütze\", \"sets\":4}";
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Liegestütze")));
    }
    // Test: Validierung greift - Blockiert Workouts mit 0 Sätzen
    @Test
    void shouldRejectWorkoutWithZeroSets() throws Exception {
        String invalidJson = "{\"name\": \"Bankdrücken\", \"sets\": 0, \"date\": \"2026-05-07\"}";

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    // Test: Erfolgreiches Speichern und korrekte JSON-Rückgabe
    @Test
    void shouldCreateValidWorkout() throws Exception {
        Workout validWorkout = new Workout();
        validWorkout.setName("Kreuzheben");
        validWorkout.setSets(4);

        when(service.save(any(Workout.class))).thenReturn(validWorkout);

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Kreuzheben\", \"sets\": 4, \"date\": \"2026-05-07\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kreuzheben"))
                .andExpect(jsonPath("$.sets").value(4));
    }

    // Test: Leere Datenbank liefert ein leeres JSON-Array zurück
    @Test
    void shouldReturnEmptyListWhenNoWorkouts() throws Exception {
        when(service.getAllWorkouts()).thenReturn(List.of());

        mockMvc.perform(get("/workouts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}