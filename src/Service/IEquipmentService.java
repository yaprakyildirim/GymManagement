package Service;

import Entity.EquipmentEntity;

import java.util.ArrayList;

public interface IEquipmentService {
    EquipmentEntity getEquipmentById(int _id);
    EquipmentEntity saveEquipment(EquipmentEntity _equipment);
    ArrayList<EquipmentEntity> getAllEquipments();
    boolean deleteEquipment(EquipmentEntity _equipment);
    ArrayList<EquipmentEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<EquipmentEntity> _equipmentList);
}
