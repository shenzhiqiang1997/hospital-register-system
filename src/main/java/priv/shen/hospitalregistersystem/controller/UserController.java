package priv.shen.hospitalregistersystem.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.shen.hospitalregistersystem.dto.*;
import priv.shen.hospitalregistersystem.service.UserService;
import priv.shen.hospitalregistersystem.vo.LoginVO;
import priv.shen.hospitalregistersystem.vo.Result;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("发送短信验证码")
    @PostMapping("/message/{telNum}")
    public Result message(@PathVariable("telNum")String telNum) throws Exception {
        return userService.sendCode(telNum);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto) throws Exception {
        return userService.register(registerDto);
    }


    @ApiOperation("密码登录")
    @PostMapping("/loginByPassword")
    public Result<LoginVO> loginByPassword(@RequestBody LoginByPasswordDto loginByPasswordDto,
                                           @ApiParam(hidden = true) HttpSession session) throws Exception {
        return userService.loginByPassword(loginByPasswordDto,session);
    }

    @ApiOperation("短信验证码登录")
    @PostMapping("/loginByCode")
    public Result<LoginVO> loginByCode(@RequestBody LoginByCodeDto loginByCodeDto,
                                       @ApiParam(hidden = true) HttpSession session) throws Exception {
        return userService.loginByCodeDto(loginByCodeDto,session);
    }

    @ApiOperation("退出登录")
    @DeleteMapping("/logout")
    public Result logout(HttpSession session) throws Exception{
        return userService.logout(session);
    }

    @ApiOperation("验证短信验证码")
    @PostMapping("/validate")
    public Result validate(@RequestBody ValidateDto validateDto) throws Exception {
        return userService.validate(validateDto);
    }

    @ApiOperation("更改性别")
    @PutMapping("/sex")
    public Result updateSex(@RequestBody SexDto sexDto,HttpSession session) throws Exception{
        return userService.updateSex(sexDto,session);
    }

    @ApiOperation("更改密码")
    @PutMapping("/psw")
    public Result updatePassword(@RequestBody PasswordDto passwordDto,HttpSession session) throws Exception {
        return userService.updatePassword(passwordDto,session);
    }

}
