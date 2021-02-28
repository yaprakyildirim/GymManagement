package Service.Impl;

import Entity.ManagerEntity;
import Repository.IDataAccess;
import Service.IManagerService;
import Service.IPersonService;

import java.util.ArrayList;

public class ManagerServiceImpl implements IManagerService, IPersonService {
    private IDataAccess repository;

    public ManagerServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public ManagerEntity getManagerById(int _id) {
        ArrayList<ManagerEntity> managerList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(managerList.isEmpty()){
            return null;
        }
        return managerList.get(0);
    }

    @Override
    public ManagerEntity saveManager(ManagerEntity _manager) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<ManagerEntity> managerList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (ManagerEntity manager : managerList) {
            if (manager.getId() == _manager.getId()) {
                manager.setName(_manager.getName());
                manager.setSurname(_manager.getSurname());
                manager.setPhoneNumber(_manager.getPhoneNumber());
                manager.setPassword(_manager.getPassword());
                manager.setStatus(_manager.isStatus());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = managerList.size() == 0 ? 1 : (managerList.get(managerList.size() - 1).getId() + 1);
            _manager.setId(id);
            managerList.add(_manager);
        }
        this.repository.save(this.convertEntityListToStringList(managerList));
        return _manager;
    }

    @Override
    public ArrayList<ManagerEntity> getAllManagers() {
        ArrayList<String> managerList = this.repository.getAll();
        if(!managerList.isEmpty()){
            return this.convertStringListToEntityList(managerList);
        }
        return null;
    }

    @Override
    public boolean deleteManager(ManagerEntity _manager) {
        return this.repository.delete(_manager.getId());
    }

    @Override
    public ArrayList<ManagerEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<ManagerEntity> managerList = new ArrayList<ManagerEntity>();
        for (String item : _strList) {
            ManagerEntity customer = new ManagerEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    item.split("\t")[1],
                    item.split("\t")[2],
                    item.split("\t")[3],
                    item.split("\t")[4],
                    Boolean.parseBoolean(item.split("\t")[5])
            );
            managerList.add(customer);
        }
        return managerList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<ManagerEntity> _managerList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (ManagerEntity manager : _managerList) {
            strList.add(
                    manager.getId() + "\t" +
                            manager.getName() + "\t" +
                            manager.getSurname() + "\t" +
                            manager.getPhoneNumber() + "\t" +
                            manager.getPassword() + "\t" +
                            manager.isStatus() + "\n"
            );
        }
        return strList;
    }

    @Override
    public Object login(String _name, String _password) {
        ArrayList<ManagerEntity> allManagers = this.getAllManagers();
        ManagerEntity currentManager = null;
        for(ManagerEntity manager : allManagers){
            if(manager.getName().equals(_name) && manager.getPassword().equals(_password)){
                currentManager = manager;
            }
        }
        return currentManager;
    }
}
