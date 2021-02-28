package Service;

import Entity.GymHallEntity;

import java.util.ArrayList;

public interface IGymHallService {
    GymHallEntity getGymHallById(int _id);
    GymHallEntity saveGymHall(GymHallEntity _gymHall);
    ArrayList<GymHallEntity> getAllGymHalls();
    boolean deleteGymHall(GymHallEntity _gymHall);
    ArrayList<GymHallEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<GymHallEntity> _gymHallList);
}
