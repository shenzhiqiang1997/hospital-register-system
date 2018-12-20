package priv.shen.hospitalregistersystem.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import priv.shen.hospitalregistersystem.dto.DoctorNameDto;
import priv.shen.hospitalregistersystem.entity.Doctor;
import priv.shen.hospitalregistersystem.service.DoctorService;
import priv.shen.hospitalregistersystem.vo.Result;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @ApiOperation("搜索医生")
    @PostMapping("/doctor/search")
    public Result<List<Doctor>> search(@RequestBody DoctorNameDto doctorNameDto){
       return doctorService.search(doctorNameDto);
    }
}
