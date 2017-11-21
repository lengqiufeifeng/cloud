package logan.exemple.service.action;

import logan.exemple.service.common.Result;
import logan.exemple.service.common.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by qiufeng on 2017/9/20.
 */
@Controller
class HttpExceptionController implements ErrorViewResolver {
    private Logger logger = LoggerFactory.getLogger(HttpExceptionController.class);



    @RequestMapping(value = "/exception-error")
    @ResponseBody
    public Object error(HttpServletResponse resp, HttpServletRequest req) {
        Result r = new Result();

        String err_msg = (String) req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
//        if (StringUtils.isEmpty(err_msg)) {
//            err_msg = (String) req.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
//        }
        if (StringUtils.isEmpty(err_msg)) {
            err_msg = (String) req.getSession().getAttribute("err");
        }
        r.setResultCode(StatusCode.Fail.getIndex());
        if (resp.getStatus() == 404) {
            r.setErrCode("404");
            if (StringUtils.isEmpty(err_msg)) {
                r.setErrMessage("not find:" + req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
            } else {
                r.setErrMessage(err_msg);
            }
        } else {
            r.setErrCode(String.valueOf(resp.getStatus()));
            r.setErrMessage(err_msg);
            req.getSession().removeAttribute("err");
        }

        return r;
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        return new ModelAndView("/exception-error");
    }
}
