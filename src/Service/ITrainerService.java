package Service;

import Entity.CustomerEntity;
import Entity.TrainerEntity;

import java.util.ArrayList;

public interface ITrainerService {
    TrainerEntity saveTrainer(TrainerEntity _trainer);
    TrainerEntity getTrainerById(int _id);
    ArrayList<TrainerEntity> getAllTrainers();
    boolean deleteTrainer(TrainerEntity _trainer);
    ArrayList<TrainerEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<TrainerEntity> _trainerList);
}
