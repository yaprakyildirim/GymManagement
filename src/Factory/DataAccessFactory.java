package Factory;

import Repository.IDataAccess;
import Repository.Impl.TxtRepository;

public class DataAccessFactory {

    public static IDataAccess getRepository(String _type,String _args){
        if(_type.toLowerCase() == "txt"){
            return new TxtRepository(_args);
        }
        return null;
    }
}
