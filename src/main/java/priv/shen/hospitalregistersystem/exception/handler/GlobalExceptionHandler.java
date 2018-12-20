package priv.shen.hospitalregistersystem.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.shen.hospitalregistersystem.constant.Message;
import priv.shen.hospitalregistersystem.exception.GlobalException;
import priv.shen.hospitalregistersystem.vo.Result;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(Exception e){


        Result<String> result = new Result<>();
        result.setSuccess(false);

        if (e instanceof GlobalException){
            result.setMessage(e.getMessage());
        }else{
            e.printStackTrace();
            result.setMessage(Message.ERROR.content);
        }

        return result;
    }
}
