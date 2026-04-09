package de.htw_berlin.wtprojekt;

public class Workout {
    private String exerciseName;
    private int sets;

    public Workout() {}

    public Workout(String exerciseName, int sets) {
        this.exerciseName = exerciseName;
        this.sets = sets;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}