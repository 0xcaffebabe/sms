package wang.ismy.manage1.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.ismy.manage1.dto.Result;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result process(RuntimeException e){
        e.printStackTrace();
        System.out.println(e.getClass());
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(e.getMessage());
        e.printStackTrace();
        return result;
    }
}
