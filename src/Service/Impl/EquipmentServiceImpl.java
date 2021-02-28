package Service.Impl;

import Entity.EquipmentEntity;
import Repository.IDataAccess;
import Service.IEquipmentService;

import java.util.ArrayList;

public class EquipmentServiceImpl implements IEquipmentService {
    
    private IDataAccess repository;

    public EquipmentServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public EquipmentEntity getEquipmentById(int _id) {
        ArrayList<EquipmentEntity> equipmentAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(equipmentAsList.isEmpty()){
            return null;
        }
        return equipmentAsList.get(0);
    }

    @Override
    public EquipmentEntity saveEquipment(EquipmentEntity _equipment) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<EquipmentEntity> equipmentList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (EquipmentEntity equipment : equipmentList) {
            if (equipment.getId() == _equipment.getId()) {
                equipment.setEquipmentName(_equipment.getEquipmentName());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = equipmentList.size() == 0 ? 1 : (equipmentList.get(equipmentList.size() - 1).getId() + 1);
            _equipment.setId(id);
            equipmentList.add(_equipment);
        }
        this.repository.save(this.convertEntityListToStringList(equipmentList));
        return _equipment;
    }

    @Override
    public ArrayList<EquipmentEntity> getAllEquipments() {
        if (this.repository != null) {
            ArrayList<String> equipmentList = this.repository.getAll();
            if (!equipmentList.isEmpty()) {
                return this.convertStringListToEntityList(equipmentList);
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteEquipment(EquipmentEntity _equipment) {
        return this.repository.delete(_equipment.getId());
    }

    @Override
    public ArrayList<EquipmentEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<EquipmentEntity> equipmentList = new ArrayList<EquipmentEntity>();
        for (String item : _strList) {
            EquipmentEntity equipment = new EquipmentEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    item.split("\t")[1]
            );
            equipmentList.add(equipment);
        }
        return equipmentList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<EquipmentEntity> _equipmentList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (EquipmentEntity equipment : _equipmentList) {
            strList.add(
                    equipment.getId() + "\t" +
                            equipment.getEquipmentName() + "\n"
            );
        }
        return strList;
    }

}
