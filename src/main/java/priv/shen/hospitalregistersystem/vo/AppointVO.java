package priv.shen.hospitalregistersystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointVO {
    private List<DoctorVO> doctors;
}
