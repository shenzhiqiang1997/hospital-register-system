package priv.shen.hospitalregistersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.shen.hospitalregistersystem.entity.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Schedule findByDepartmentAndDateAndTime(String department, Date date,String time);
}
