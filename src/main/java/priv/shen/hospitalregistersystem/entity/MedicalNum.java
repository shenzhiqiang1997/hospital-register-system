package priv.shen.hospitalregistersystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medical_num", schema = "hospital_register_system")
public class MedicalNum {
    private long id;
    private long doctorId;
    private long scheduleId;
    private int fee;
    private int quota;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "doctor_id", nullable = false)
    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "schedule_id", nullable = false)
    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
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
    @Column(name = "quota", nullable = false)
    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalNum that = (MedicalNum) o;
        return id == that.id &&
                doctorId == that.doctorId &&
                scheduleId == that.scheduleId &&
                fee == that.fee &&
                quota == that.quota;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, doctorId, scheduleId, fee, quota);
    }
}
