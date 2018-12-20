package priv.shen.hospitalregistersystem.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.shen.hospitalregistersystem.service.AppointService;
import priv.shen.hospitalregistersystem.vo.AppointVO;
import priv.shen.hospitalregistersystem.vo.Result;

import javax.servlet.http.HttpSession;

@RestController
public class AppointController {
    @Autowired
    private AppointService appointService;

    @ApiOperation("查看可预约的挂号列表")
    @GetMapping("/appoint/{code}")
    public Result<AppointVO> get(@PathVariable("code")Long code) throws Exception {
        return appointService.get(code);
    }

    @ApiOperation("预约挂号")
    @PostMapping("/makeAppoint/{appointId}")
    public Result makeAppoint(@PathVariable("appointId")Long appointId, HttpSession session) throws Exception {
        return appointService.makeAppoint(appointId,session);
    }
}
