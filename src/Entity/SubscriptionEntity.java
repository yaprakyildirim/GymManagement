package Entity;

import java.util.Date;

public class SubscriptionEntity {
    private int id;
    private Date endDate;
    private int customerId;
    private int trainerId;
    private int exerciseId;

    public SubscriptionEntity(int id, Date endDate, int customerId, int trainerId, int exerciseId) {
        this.id = id;
        this.endDate = endDate;
        this.customerId = customerId;
        this.trainerId = trainerId;
        this.exerciseId = exerciseId;
    }

    public SubscriptionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
