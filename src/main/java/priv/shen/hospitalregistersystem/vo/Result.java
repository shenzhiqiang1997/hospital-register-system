package priv.shen.hospitalregistersystem.vo;

import lombok.Data;

@Data
public class Result<T> {
    private boolean success;
    private T data;
    private String message;

    public Result(){

    }

    public Result(boolean success){
        this.success = success;
    }
    public Result(boolean success,T data){
        this.success = success;
        this.data = data;
    }

    public Result(boolean success,String message){
        this.success = success;
        this.message = message;
    }
}
