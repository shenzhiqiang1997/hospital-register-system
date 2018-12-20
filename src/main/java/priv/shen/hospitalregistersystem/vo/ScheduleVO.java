package priv.shen.hospitalregistersystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleVO {
    private DayVO[] days;
    private RestVO[] rests;
    private String[] docList;
}
