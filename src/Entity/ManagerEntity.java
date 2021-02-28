package Entity;

public class ManagerEntity extends AbsPersonEntity {
    public ManagerEntity() {
    }

    public ManagerEntity(int id, String name, String surname, String phoneNumber, String password, boolean status) {
        super(id, name, surname, phoneNumber, password, status);
    }

    @Override
    String getPersonType() {
        return "admin";
    }
}
