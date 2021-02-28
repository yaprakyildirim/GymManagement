package Entity;

import java.util.Date;

public class TrainerEntity extends AbsPersonEntity{
    private double salary;
    private int hallId;
    private Date startDate;
    private Date endDate;

    public TrainerEntity() {
    }

    public TrainerEntity(double salary, int hallId, Date startDate, Date endDate) {
        this.salary = salary;
        this.hallId = hallId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TrainerEntity(int id, String name, String surname, String phoneNumber,String password, boolean status, double salary, int hallId, Date startDate, Date endDate) {
        super(id, name, surname, phoneNumber,password, status);
        this.salary = salary;
        this.hallId = hallId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    String getPersonType() {
        return "trainer";
    }
}
