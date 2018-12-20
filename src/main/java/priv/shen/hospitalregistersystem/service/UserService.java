package priv.shen.hospitalregistersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import priv.shen.hospitalregistersystem.constant.Code;
import priv.shen.hospitalregistersystem.constant.Key;
import priv.shen.hospitalregistersystem.constant.Message;
import priv.shen.hospitalregistersystem.dto.*;
import priv.shen.hospitalregistersystem.entity.User;
import priv.shen.hospitalregistersystem.exception.GlobalException;
import priv.shen.hospitalregistersystem.repository.UserRepository;
import priv.shen.hospitalregistersystem.util.SendCode;
import priv.shen.hospitalregistersystem.vo.LoginVO;
import priv.shen.hospitalregistersystem.vo.Result;
import priv.shen.hospitalregistersystem.vo.UserInfoVO;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Result sendCode(String telNum) throws Exception {
      CodeResult codeResult = SendCode.send(telNum);
      if (codeResult.getCode() == Code.SUCCESS.value) {
          stringRedisTemplate.opsForValue().set(telNum, codeResult.getObj(),5,TimeUnit.MINUTES);
          return new Result(true);
      }
      else if (codeResult.getCode() == Code.FREQUENT.value)
          throw new GlobalException(Message.MESSAGE_FREQUENT.content);
      else
          throw new GlobalException(Message.MESSAGE_FAILURE.content);
    }


    public Result validate(ValidateDto validateDto) throws Exception{
        String telNum = validateDto.getTelNum();
        String code = validateDto.getCode();
        String savedCode = stringRedisTemplate.opsForValue().get(telNum);
        if (code!=null&&code.equals(savedCode)) {
            stringRedisTemplate.opsForValue().set(telNum,"true",3,TimeUnit.MINUTES);
            return new Result<>(true);
        }else {
            throw new GlobalException(Message.VALIDATE_FAILURE.content);
        }
    }

    public Result register(RegisterDto registerDto) throws Exception{

        String validated = stringRedisTemplate.opsForValue().get(registerDto.getPhone());
        if (!"true".equals(validated))
            throw new GlobalException(Message.TIMEOUT.content);
        else if(!registerDto.getIdNumber().equals(registerDto.getIdNumber2())){
            throw new GlobalException(Message.ID_NUM_NOT_SAME.content);
        } else if (!registerDto.getPsw().equals(registerDto.getPsw2())){
            throw new GlobalException(Message.PASSWORD_NOT_SAME.content);
        } else if (userRepository.existsById(registerDto.getPhone())){
            throw new GlobalException(Message.USER_EXISTS.content);
        } else{
            User user = new User(registerDto);
            userRepository.save(user);
            return new Result(true);
        }
    }

    public Result<LoginVO> loginByPassword(LoginByPasswordDto loginByPasswordDto, HttpSession session) throws Exception{

        if (!userRepository.existsById(loginByPasswordDto.getTelNum())){
            throw new GlobalException(Message.USER_NOT_EXISTS.content);
        }

        User user = userRepository.getOne(loginByPasswordDto.getTelNum());
        if (user.getPassword().equals(loginByPasswordDto.getPassword())){
            session.setAttribute(Key.TEL_NUM.value,loginByPasswordDto.getTelNum());
            return new Result<>(true,new LoginVO(user.getName()));
        }else {
            throw new GlobalException(Message.PASSWORD_ERROR.content);
        }

    }

    public Result<LoginVO> loginByCodeDto(LoginByCodeDto loginByCodeDto, HttpSession session) throws Exception{
        if (!userRepository.existsById(loginByCodeDto.getTelNum())){
            throw new GlobalException(Message.USER_NOT_EXISTS.content);
        }

        String savedCode = stringRedisTemplate.opsForValue().get(loginByCodeDto.getTelNum());
        if (savedCode!=null&&savedCode.equals(loginByCodeDto.getCode())){
            User user = userRepository.getOne(loginByCodeDto.getTelNum());
            session.setAttribute(Key.TEL_NUM.value,loginByCodeDto.getTelNum());
            return new Result<>(true,new LoginVO(user.getName()));
        }else {
            throw new GlobalException(Message.VALIDATE_FAILURE.content);
        }
    }

    public Result updateSex(SexDto sexDto, HttpSession session) throws Exception{
        String telNum = (String) session.getAttribute(Key.TEL_NUM.value);
        User user = userRepository.getOne(telNum);
        user.setSex(sexDto.getSex());
        userRepository.saveAndFlush(user);
        return new Result(true);
    }

    public Result updatePassword(PasswordDto passwordDto,HttpSession session) throws Exception{
        String psw = passwordDto.getPsw();
        String psw2 = passwordDto.getPsw2();
        if (StringUtils.isEmpty(psw)||StringUtils.isEmpty(psw2))
            throw new GlobalException(Message.PASSWORD_EMPTY.content);

        if (!psw.equals(psw2))
            throw new GlobalException(Message.PASSWORD_NOT_SAME.content);

        String telNum = (String) session.getAttribute(Key.TEL_NUM.value);
        User user = userRepository.getOne(telNum);
        user.setPassword(psw);
        userRepository.saveAndFlush(user);

        return new Result(true);
    }

    public Result logout(HttpSession session) throws Exception {
        String telNum = (String) session.getAttribute(Key.TEL_NUM.value);
        if (telNum == null)
            throw new GlobalException(Message.NOT_LOGIN.content);
        session.invalidate();
        return new Result(true);
    }

    public Result<UserInfoVO> getUserInfo(HttpSession session) throws Exception{
        String telNum = (String) session.getAttribute(Key.TEL_NUM.value);
        User user = userRepository.getOne(telNum);

        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setName(user.getName());
        userInfoVO.setSex(user.getSex());
        userInfoVO.setIdNum(user.getIdNum());
        userInfoVO.setTelNum(user.getTelNum());
        return new Result<>(true,userInfoVO);
    }
}
