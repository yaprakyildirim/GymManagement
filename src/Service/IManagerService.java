package Service;

import Entity.ManagerEntity;
import java.util.ArrayList;

public interface IManagerService {
    ManagerEntity getManagerById(int _id);
    ManagerEntity saveManager(ManagerEntity _manager);
    ArrayList<ManagerEntity> getAllManagers();
    boolean deleteManager(ManagerEntity _manager);
    ArrayList<ManagerEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<ManagerEntity> _managerList);
}
