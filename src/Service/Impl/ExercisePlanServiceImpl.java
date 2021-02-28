package Service.Impl;

import Entity.CustomerEntity;
import Entity.ExercisePlanEntity;
import Entity.TrainerEntity;
import Repository.IDataAccess;
import Service.IExercisePlanService;

import java.util.ArrayList;

public class ExercisePlanServiceImpl implements IExercisePlanService {
    private IDataAccess repository;

    public ExercisePlanServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public ExercisePlanEntity getExercisePlanById(int _id) {
        ArrayList<ExercisePlanEntity> exercisePlanAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(exercisePlanAsList.isEmpty()){
            return null;
        }
        return exercisePlanAsList.get(0);
    }

    @Override
    public ExercisePlanEntity saveExercisePlan(ExercisePlanEntity _exercisePlan) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<ExercisePlanEntity> exercisePlanList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (ExercisePlanEntity exercisePlan : exercisePlanList) {
            if (exercisePlan.getId() == _exercisePlan.getId()) {
                exercisePlan.setTrainerId(_exercisePlan.getTrainerId());
                exercisePlan.setEquipmentId(_exercisePlan.getEquipmentId());
                exercisePlan.setDuration(_exercisePlan.getDuration());
                exercisePlan.setExerciseName(_exercisePlan.getExerciseName());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = exercisePlanList.size() == 0 ? 1 : (exercisePlanList.get(exercisePlanList.size() - 1).getId() + 1);
            _exercisePlan.setId(id);
            exercisePlanList.add(_exercisePlan);
        }
        this.repository.save(this.convertEntityListToStringList(exercisePlanList));
        return _exercisePlan;
    }

    @Override
    public ArrayList<ExercisePlanEntity> getAllExercisePlans() {
        if (this.repository != null) {
            ArrayList<String> exercisePlanList = this.repository.getAll();
            if (!exercisePlanList.isEmpty()) {
                return this.convertStringListToEntityList(exercisePlanList);
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteExercisePlan(ExercisePlanEntity _exercisePlan) {
        return this.repository.delete(_exercisePlan.getId());
    }

    @Override
    public ArrayList<ExercisePlanEntity> findTrainerExercisePlan(TrainerEntity _trainer) {
        ArrayList<ExercisePlanEntity> allExercisePlans = this.getAllExercisePlans();
        ArrayList<ExercisePlanEntity> trainerPlans = new ArrayList<ExercisePlanEntity>();
        for(ExercisePlanEntity exercise : allExercisePlans){
            if(exercise.getTrainerId() == _trainer.getId()){
                trainerPlans.add(exercise);
            }
        }
        return trainerPlans;
    }

    @Override
    public ArrayList<ExercisePlanEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<ExercisePlanEntity> exercisePlanList = new ArrayList<ExercisePlanEntity>();
        for (String item : _strList) {
            ExercisePlanEntity exercisePlan = new ExercisePlanEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    Integer.parseInt(item.split("\t")[1]),
                    item.split("\t")[2],
                    item.split("\t")[3],
                    item.split("\t")[4]
            );
            exercisePlanList.add(exercisePlan);
        }
        return exercisePlanList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<ExercisePlanEntity> _exercisePlanList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (ExercisePlanEntity exercisePlan : _exercisePlanList) {
            strList.add(
                    exercisePlan.getId() + "\t" +
                            exercisePlan.getTrainerId() + "\t" +
                            exercisePlan.getEquipmentId() + "\t" +
                            exercisePlan.getDuration() + "\t"+
                            exercisePlan.getExerciseName() + "\n"
            );
        }
        return strList;
    }
}
