package de.htw_berlin.wtprojekt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutController.class)
class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WorkoutRepository repository;

    @Test
    void shouldReturnWorkoutsList() throws Exception {
        Workout testWorkout = new Workout("Klimmzüge", 3);
        when(repository.findAll()).thenReturn(List.of(testWorkout));
        mockMvc.perform(get("/workouts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Klimmzüge")));
    }
    @Test
    void shouldCreateWorkout() throws Exception {
        Workout savedWorkout = new Workout("Liegestütze", 4);
        when(repository.save(org.mockito.ArgumentMatchers.any(Workout.class))).thenReturn(savedWorkout);
        String jsonPayload = "{\"name\":\"Liegestütze\", \"sets\":4}";
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Liegestütze")));
    }
}