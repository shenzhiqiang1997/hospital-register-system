package priv.shen.hospitalregistersystem.dto;

import lombok.Getter;

@Getter
public class LoginByCodeDto {
    private String telNum;
    private String code;
}
