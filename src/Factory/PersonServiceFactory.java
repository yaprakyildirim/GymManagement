package Factory;

import Service.IPersonService;
import Service.Impl.CustomerServiceImpl;
import Service.Impl.ManagerServiceImpl;
import Service.Impl.TrainerServiceImpl;

public class PersonServiceFactory {
    public static IPersonService getService(int personType){
        IPersonService service = null;
        switch (personType){
            case 0:
                service = new TrainerServiceImpl(DataAccessFactory.getRepository("txt","trainers.txt"));
                break;
            case 1:
                service = new ManagerServiceImpl(DataAccessFactory.getRepository("txt","managers.txt"));
                break;
            case 2:
                service = new CustomerServiceImpl(DataAccessFactory.getRepository("txt","customers.txt"));
        }
        return service;
    }
}
