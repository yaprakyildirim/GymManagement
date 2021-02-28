package Service;

import Entity.CustomerEntity;
import Entity.ExercisePlanEntity;
import Entity.TrainerEntity;

import java.util.ArrayList;

public interface IExercisePlanService {
    ExercisePlanEntity getExercisePlanById(int _id);
    ExercisePlanEntity saveExercisePlan(ExercisePlanEntity _exercisePlan);
    ArrayList<ExercisePlanEntity> getAllExercisePlans();
    boolean deleteExercisePlan(ExercisePlanEntity _exercisePlan);
    ArrayList<ExercisePlanEntity> findTrainerExercisePlan(TrainerEntity _trainer);
    ArrayList<ExercisePlanEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<ExercisePlanEntity> _exercisePlanList);
}
