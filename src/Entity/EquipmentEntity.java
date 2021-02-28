package Entity;

public class EquipmentEntity {
    private int id;
    private String equipmentName;

    public EquipmentEntity(int id, String equipmentName) {
        this.id = id;
        this.equipmentName = equipmentName;
    }

    public EquipmentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
