package priv.shen.hospitalregistersystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorVO {
    private String name;
    private String major;
    private Integer charge;
    private Integer restNum;
    private Long appointId;
}
