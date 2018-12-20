package priv.shen.hospitalregistersystem.constant;

public enum Message {
    ERROR("未知错误"), MESSAGE_FREQUENT("您获取短信的频率太过频繁"),MESSAGE_FAILURE("验证码获取失败")
    ,VALIDATE_FAILURE("验证码错误"),USER_EXISTS("当前手机号已注册"),USER_NOT_EXISTS("当前手机号未注册")
    ,PASSWORD_ERROR("密码错误"),TIMEOUT("操作超时，请重新注册"),ID_NUM_NOT_SAME("两次身份证号必须一致")
    ,PASSWORD_NOT_SAME("两次密码必须一致"),RESTLESS("剩余号不足"),UN_AUTHENTICATION("身份认证失败，您无权删除")
    ,ORDER_TIMEOUT("预约号已过期，无法取消"),PASSWORD_EMPTY("密码不能为空"),NOT_LOGIN("您尚未登录或登录状态已经失效");
    public String content;
    Message(String content){
        this.content = content;
    }
}
