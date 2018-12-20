package priv.shen.hospitalregistersystem.entity;

import priv.shen.hospitalregistersystem.dto.RegisterDto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    private String telNum;
    private String password;
    private String name;
    private String sex;
    private String idNum;
    private String medicareCardNum;
    public User(){}
    public User(RegisterDto registerDto){
        this.telNum = registerDto.getPhone();
        this.password = registerDto.getPsw();
        this.name = registerDto.getName();
        this.sex = registerDto.getSex();
        this.idNum = registerDto.getIdNumber();
        this.medicareCardNum = registerDto.getMedicareCardNum();
    }

    @Id
    @Column(name = "tel_num", nullable = false, length = 11)
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 16)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex", nullable = false, length = 1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "id_num", nullable = false, length = 18)
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }


    @Basic
    @Column(name = "medicare_card_num", nullable = false, length = 16)
    public String getMedicareCardNum() {
        return medicareCardNum;
    }

    public void setMedicareCardNum(String medicareCardNum) {
        this.medicareCardNum = medicareCardNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(telNum, user.telNum) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(idNum, user.idNum)  &&
                Objects.equals(medicareCardNum, user.medicareCardNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(telNum, password, name, sex, idNum, medicareCardNum);
    }
}
