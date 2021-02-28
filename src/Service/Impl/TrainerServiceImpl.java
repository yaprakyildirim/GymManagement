package Service.Impl;

import Entity.TrainerEntity;
import Repository.IDataAccess;
import Service.IPersonService;
import Service.ITrainerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TrainerServiceImpl implements ITrainerService, IPersonService {
    private IDataAccess repository;

    public TrainerServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public TrainerEntity saveTrainer(TrainerEntity _trainer) {
        ArrayList<TrainerEntity> trainerEntityArrayList = this.convertStringListToEntityList(this.repository.getAll());
        boolean isUpdated = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (TrainerEntity trainer : trainerEntityArrayList){
            if(trainer.getId() == _trainer.getId()){
                trainer.setName(_trainer.getName());
                trainer.setSurname(_trainer.getSurname());
                trainer.setPhoneNumber(_trainer.getPhoneNumber());
                trainer.setPassword(_trainer.getPassword());
                trainer.setStatus(_trainer.isStatus());
                trainer.setSalary(_trainer.getSalary());
                trainer.setHallId(_trainer.getHallId());
                trainer.setStartDate(_trainer.getStartDate());
                trainer.setEndDate(_trainer.getEndDate());
                isUpdated = true;
            }
        }
        if(!isUpdated){
            int id = trainerEntityArrayList.size() == 0 ? 1 : (trainerEntityArrayList.get(trainerEntityArrayList.size() - 1).getId() + 1);
            _trainer.setId(id);
            trainerEntityArrayList.add(_trainer);
        }
        this.repository.save(this.convertEntityListToStringList(trainerEntityArrayList));
        return _trainer;
    }

    @Override
    public TrainerEntity getTrainerById(int _id) {
        ArrayList<TrainerEntity> trainersAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(trainersAsList.isEmpty()){
            return null;
        }
        return trainersAsList.get(0);
    }

    @Override
    public ArrayList<TrainerEntity> getAllTrainers() {
        return this.convertStringListToEntityList(
                this.repository.getAll()
        );
    }

    @Override
    public boolean deleteTrainer(TrainerEntity _trainer) {
        return this.repository.delete(_trainer.getId());
    }

    @Override
    public ArrayList<TrainerEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<TrainerEntity> customerList = new ArrayList<TrainerEntity>();
        for (String item : _strList) {
            TrainerEntity customer = null;

                customer = new TrainerEntity(
                        Integer.parseInt(item.split("\t")[0]),
                        item.split("\t")[1],
                        item.split("\t")[2],
                        item.split("\t")[3],
                        item.split("\t")[4],
                        Boolean.parseBoolean(item.split("\t")[5]),
                        Double.parseDouble(item.split("\t")[6]),
                        Integer.parseInt(item.split("\t")[7]),
                        new Date(Long.parseLong(item.split("\t")[8])),
                        new Date(Long.parseLong(item.split("\t")[9]))
                );

            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<TrainerEntity> _trainerList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (TrainerEntity trainer : _trainerList) {
            strList.add(
                    trainer.getId() + "\t" +
                            trainer.getName() + "\t" +
                            trainer.getSurname() + "\t" +
                            trainer.getPhoneNumber() + "\t" +
                            trainer.getPassword() + "\t" +
                            trainer.isStatus() + "\t" +
                            trainer.getSalary() + "\t" +
                            trainer.getHallId() + "\t" +
                            trainer.getStartDate().getTime() + "\t" +
                            trainer.getEndDate().getTime() + "\n"
            );
        }
        return strList;
    }

    @Override
    public Object login(String _name, String _password) {
        ArrayList<TrainerEntity> trainerEntities = this.getAllTrainers();
        for (TrainerEntity entity : trainerEntities){
            if(entity.getName().equals(_name) && entity.getPassword().equals(_password)){
                return entity;
            }
        }
        return null;
    }
}
