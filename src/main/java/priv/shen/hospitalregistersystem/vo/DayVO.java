package priv.shen.hospitalregistersystem.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayVO {
    private String day;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;
}
