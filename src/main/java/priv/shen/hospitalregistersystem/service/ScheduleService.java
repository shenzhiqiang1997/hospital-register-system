
package priv.shen.hospitalregistersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.shen.hospitalregistersystem.dto.ScheduleDto;
import priv.shen.hospitalregistersystem.entity.Schedule;
import priv.shen.hospitalregistersystem.repository.DoctorRepository;
import priv.shen.hospitalregistersystem.repository.MedicalNumRepository;
import priv.shen.hospitalregistersystem.repository.ScheduleRepository;
import priv.shen.hospitalregistersystem.vo.DayVO;
import priv.shen.hospitalregistersystem.vo.RestVO;
import priv.shen.hospitalregistersystem.vo.Result;
import priv.shen.hospitalregistersystem.vo.ScheduleVO;

import java.util.*;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MedicalNumRepository medicalNumRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    private static final String[] weekDays={"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};

    public Result<ScheduleVO> get(ScheduleDto scheduleDto) throws Exception{
        String department = scheduleDto.getDepartment();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        DayVO[] days = new DayVO[7];
        RestVO[] rests = new RestVO[7*3];

        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE,1);
            Date date = calendar.getTime();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
            String day = weekDays[dayOfWeek];
            days[i]=new DayVO(day,date);

            Schedule schedule = scheduleRepository.findByDepartmentAndDateAndTime(department,date,"上午");
            if (schedule !=null){
                Long code = schedule.getId();
                System.out.println(code);
                Integer rest = medicalNumRepository.sumRestByScheduleId(code);
                if (rest!=null){
                    RestVO restVO = new RestVO(rest,code);
                    rests[i]=restVO;
                }
            }

            schedule = scheduleRepository.findByDepartmentAndDateAndTime(department,date,"下午");
            if (schedule !=null){
                Long code = schedule.getId();
                Integer rest = medicalNumRepository.sumRestByScheduleId(code);
                if (rest!=null){
                    RestVO restVO = new RestVO(rest,code);
                    rests[i+7]=restVO;
                }
            }

            schedule = scheduleRepository.findByDepartmentAndDateAndTime(department,date,"晚上");
            if (schedule !=null){
                Long code = schedule.getId();
                Integer rest = medicalNumRepository.sumRestByScheduleId(code);
                if (rest!=null){
                    RestVO restVO = new RestVO(rest,code);
                    rests[i+14]=restVO;
                }
            }
        }

        String[] doctors = doctorRepository.findDoctorNamesByDepartment(department);

        return new Result<>(true,new ScheduleVO(days,rests,doctors));

    }
}
