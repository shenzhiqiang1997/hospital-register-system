package priv.shen.hospitalregistersystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    private String name;
    private String sex;
    private String telNum;
    private String idNum;
}
