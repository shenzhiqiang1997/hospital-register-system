package priv.shen.hospitalregistersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.shen.hospitalregistersystem.dto.DoctorNameDto;
import priv.shen.hospitalregistersystem.entity.Doctor;
import priv.shen.hospitalregistersystem.repository.DoctorRepository;
import priv.shen.hospitalregistersystem.vo.Result;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Result<List<Doctor>> search(DoctorNameDto doctorNameDto) {
        String doctorName = "%"+doctorNameDto.getName()+"%";
        List<Doctor> doctors = doctorRepository.findAllByNameLike(doctorName);
        return new Result<>(true,doctors);
    }
}
