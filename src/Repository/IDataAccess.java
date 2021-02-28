package Repository;

import java.util.ArrayList;

public interface IDataAccess {
    ArrayList<String> getAll();
    boolean save(ArrayList<String> arr);
    boolean delete(int _id);
    ArrayList<String> getById(int _id);

}
