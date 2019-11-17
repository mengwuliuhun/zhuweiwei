package cn.kgc.ssm.ExceptionHandler;

import cn.kgc.ssm.util.JSONData;
import com.sun.corba.se.impl.io.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value =TypeMismatchException.class )
    @ResponseBody
    public JSONData Typeexception(TypeMismatchException e)
    {
        JSONData data=new JSONData();
        data.setErrorCode(-20);
        data.setMessage("数字格式不匹配");
        return data;
    }
    @ExceptionHandler
    @ResponseBody
    private JSONData exception(Exception e)
    {
        JSONData data=new JSONData();
        data.setErrorCode(-23);
        if(e.getMessage()!=null&&!"".equals(e.getMessage()))
        {
            data.setMessage(e.getMessage());
        }else {
            data.setMessage(e.toString());
        }
        return data;
    }

}
