package priv.shen.hospitalregistersystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order_", schema = "hospital_register_system", catalog = "")
public class Order {
    private long id;
    @JsonIgnore
    private String telNum;
    @JsonIgnore
    private long medicalNumId;
    private Date date;
    private String time;
    private String department;
    private String doctor;
    private String name;
    private int fee;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tel_num", nullable = false, length = 11)
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Basic
    @Column(name = "medical_num_id", nullable = false)
    public long getMedicalNumId() {
        return medicalNumId;
    }

    public void setMedicalNumId(long medicalNumId) {
        this.medicalNumId = medicalNumId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "department", nullable = false, length = 3)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "doctor", nullable = false, length = 16)
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 16)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "fee", nullable = false)
    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "time", nullable = true, length = 2)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                medicalNumId == order.medicalNumId &&
                fee == order.fee &&
                Objects.equals(telNum, order.telNum) &&
                Objects.equals(date, order.date) &&
                Objects.equals(department, order.department) &&
                Objects.equals(doctor, order.doctor) &&
                Objects.equals(name, order.name) &&
                Objects.equals(time, order.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, telNum, medicalNumId, date, department, doctor, name, fee, time);
    }
}
