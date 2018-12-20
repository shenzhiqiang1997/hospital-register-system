package priv.shen.hospitalregistersystem.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import priv.shen.hospitalregistersystem.dto.ScheduleDto;
import priv.shen.hospitalregistersystem.service.ScheduleService;
import priv.shen.hospitalregistersystem.vo.Result;
import priv.shen.hospitalregistersystem.vo.ScheduleVO;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation("排班表")
    @PostMapping("/schedule")
    public Result<ScheduleVO> get(@RequestBody ScheduleDto scheduleDto) throws Exception{
        return scheduleService.get(scheduleDto);
    }


}
