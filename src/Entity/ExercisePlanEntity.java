package Entity;

public class ExercisePlanEntity {
    private int id;
    private int trainerId;
    private String equipmentId;
    private String duration;
    private String exerciseName;

    public ExercisePlanEntity(int id, int trainerId, String equipmentId, String duration, String exerciseName) {
        this.id = id;
        this.trainerId = trainerId;
        this.equipmentId = equipmentId;
        this.duration = duration;
        this.exerciseName = exerciseName;
    }

    public ExercisePlanEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
