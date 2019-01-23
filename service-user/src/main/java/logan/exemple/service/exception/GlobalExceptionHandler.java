package logan.exemple.service.exception;

import logan.exemple.service.common.Result;
import logan.exemple.service.common.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qiufeng on 2017/9/20.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 系统异常处理，
     *
     * @param e
     * @return
     * @throwsException
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("内部异常", e);
        Result r = new Result();
        r.setErrMessage(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            r.setErrCode("404" );
        } else {
            r.setErrCode("500" );
        }
        r.setResultCode(StatusCode.Fail.getIndex());
        return r;
    }

}
