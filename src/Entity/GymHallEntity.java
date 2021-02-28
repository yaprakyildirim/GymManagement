package Entity;

import java.util.ArrayList;
import java.util.Date;

public class GymHallEntity {
    private int id;
    private Date openAt;
    private Date closeAt;
    private ArrayList<EquipmentEntity> equipmentList;
    private String hallName;

    public GymHallEntity(int id, Date openAt, Date closeAt, ArrayList<EquipmentEntity> equipmentList,String hallName) {
        this.id = id;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.equipmentList = equipmentList;
        this.hallName = hallName;
    }

    public GymHallEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOpenAt() {
        return openAt;
    }

    public void setOpenAt(Date openAt) {
        this.openAt = openAt;
    }

    public Date getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(Date closeAt) {
        this.closeAt = closeAt;
    }

    public ArrayList<EquipmentEntity> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(ArrayList<EquipmentEntity> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}
