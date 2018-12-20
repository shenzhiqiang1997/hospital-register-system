package priv.shen.hospitalregistersystem.vo;

import lombok.Data;

@Data
public class LoginVO {
    private String name;
    public LoginVO(){}
    public LoginVO(String name){
        this.name = name;
    }
}
