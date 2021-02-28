package Service.Impl;

import Entity.EquipmentEntity;
import Entity.GymHallEntity;
import Factory.DataAccessFactory;
import Repository.IDataAccess;
import Service.IGymHallService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GymHallServiceImpl implements IGymHallService {
    private IDataAccess repository;

    public GymHallServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public GymHallEntity getGymHallById(int _id) {
        ArrayList<GymHallEntity> gymHallAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if (gymHallAsList.isEmpty()) {
            return null;
        }
        return gymHallAsList.get(0);
    }

    @Override
    public GymHallEntity saveGymHall(GymHallEntity _gymHall) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<GymHallEntity> gymHallList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (GymHallEntity gymHall : gymHallList) {
            if (gymHall.getId() == _gymHall.getId()) {
                gymHall.setOpenAt(_gymHall.getOpenAt());
                gymHall.setCloseAt(_gymHall.getCloseAt());
                gymHall.setEquipmentList(_gymHall.getEquipmentList());
                gymHall.setHallName(_gymHall.getHallName());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = gymHallList.size() == 0 ? 1 : (gymHallList.get(gymHallList.size() - 1).getId() + 1);
            _gymHall.setId(id);
            gymHallList.add(_gymHall);
        }
        this.repository.save(this.convertEntityListToStringList(gymHallList));
        return _gymHall;
    }

    @Override
    public ArrayList<GymHallEntity> getAllGymHalls() {
        return this.convertStringListToEntityList(
                this.repository.getAll()
        );
    }

    @Override
    public boolean deleteGymHall(GymHallEntity _gymHall) {
        return this.repository.delete(_gymHall.getId());
    }

    @Override
    public ArrayList<GymHallEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<GymHallEntity> gymHallList = new ArrayList<GymHallEntity>();
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(DataAccessFactory.getRepository("txt", "equipments.txt"));
        for (String item : _strList) {
            GymHallEntity gymHall = new GymHallEntity();
            ArrayList<EquipmentEntity> equipmentList = new ArrayList<>();

            gymHall.setId(Integer.parseInt(item.split("\t")[0]));
            gymHall.setOpenAt(new Date(Long.parseLong(item.split("\t")[1])));
            gymHall.setCloseAt(new Date(Long.parseLong(item.split("\t")[2])));
            String[] equipmentIds = item.split("\t")[3].split(",");
            for (String equipmentId : equipmentIds) {
                equipmentList.add(equipmentService.getEquipmentById(Integer.parseInt(equipmentId)));
            }
            gymHall.setEquipmentList(equipmentList);
            gymHall.setHallName(item.split("\t")[4]);
            gymHallList.add(gymHall);
        }
        return gymHallList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<GymHallEntity> _gymHallList) {
        String equipmentIds = null;
        ArrayList<String> strList = new ArrayList<String>();
        for (GymHallEntity gymHall : _gymHallList) {
            equipmentIds = null;
            for (EquipmentEntity equipment : gymHall.getEquipmentList()) {
                if(equipmentIds == null){
                    equipmentIds = String.valueOf(equipment.getId());
                }else {
                    equipmentIds = equipmentIds+","+equipment.getId();
                }
            }
            strList.add(
                    gymHall.getId() + "\t" +
                            gymHall.getOpenAt().getTime() + "\t" +
                            gymHall.getCloseAt().getTime() + "\t" +
                            equipmentIds + "\t"+
                            gymHall.getHallName() +"\n"
            );
        }
        return strList;
    }
}
