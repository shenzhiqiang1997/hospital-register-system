package priv.shen.hospitalregistersystem.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class RegisterDto {
    private String name;
    private String sex;
    private String idNumber;
    private String idNumber2;
    private String medicareCardNum;
    private String psw;
    private String psw2;
    private String phone;
}
