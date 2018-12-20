package priv.shen.hospitalregistersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.shen.hospitalregistersystem.constant.Key;
import priv.shen.hospitalregistersystem.constant.Message;
import priv.shen.hospitalregistersystem.entity.*;
import priv.shen.hospitalregistersystem.exception.GlobalException;
import priv.shen.hospitalregistersystem.repository.*;
import priv.shen.hospitalregistersystem.vo.AppointVO;
import priv.shen.hospitalregistersystem.vo.DoctorVO;
import priv.shen.hospitalregistersystem.vo.Result;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AppointService {
    @Autowired
    private MedicalNumRepository medicalNumRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Result<AppointVO> get(Long code) throws Exception{
        List<MedicalNum> medicalNums= medicalNumRepository.findAllByScheduleId(code);
        List<DoctorVO> doctors = new LinkedList<>();
        for (MedicalNum medicalNum:
             medicalNums) {
            Doctor doctor = doctorRepository.getOne(medicalNum.getDoctorId());
            DoctorVO doctorVO = new DoctorVO(doctor.getName(),doctor.getIntro(),medicalNum.getFee(),medicalNum.getQuota(),medicalNum.getId());
            doctors.add(doctorVO);
        }
        return new Result<>(true,new AppointVO(doctors));
    }

    @Transactional
    public Result makeAppoint(Long appointId, HttpSession session) throws Exception {
        String telNum = (String) session.getAttribute(Key.TEL_NUM.value);
        MedicalNum medicalNum = medicalNumRepository.getOne(appointId);
        if (medicalNum.getQuota()>0)
            medicalNum.setQuota(medicalNum.getQuota()-1);
        else
            throw new GlobalException(Message.RESTLESS.content);
        medicalNumRepository.save(medicalNum);

        Doctor doctor = doctorRepository.getOne(medicalNum.getDoctorId());
        Schedule schedule = scheduleRepository.getOne(medicalNum.getScheduleId());
        User user = userRepository.getOne(telNum);

        Order order = new Order();
        order.setDate(schedule.getDate());
        order.setTime(schedule.getTime());
        order.setDepartment(schedule.getDepartment());
        order.setDoctor(doctor.getName());
        order.setFee(medicalNum.getFee());
        order.setMedicalNumId(medicalNum.getId());
        order.setName(user.getName());
        order.setTelNum(telNum);

        orderRepository.save(order);

        return new Result(true);
    }
}
