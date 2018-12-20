package priv.shen.hospitalregistersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priv.shen.hospitalregistersystem.entity.MedicalNum;

import java.util.List;

public interface MedicalNumRepository extends JpaRepository<MedicalNum,Long> {
    @Query(nativeQuery = true, value = "SELECT SUM(quota) FROM medical_num WHERE schedule_id =?1")
    Integer sumRestByScheduleId(Long scheduleId);
    List<MedicalNum> findAllByScheduleId(Long scheduleId);
}
