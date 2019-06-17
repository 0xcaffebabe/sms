package wang.ismy.manage1.dto;

import lombok.Data;

@Data
public class Result {
    private Boolean success;

    private String msg;

    private Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.msg = "success";
        result.success=true;
        result.data = data;
        return result;
    }

    public static Result error(String msg,Object data){
        Result result = new Result();
        result.msg = msg;
        result.success=false;
        result.data = data;
        return result;
    }
}
