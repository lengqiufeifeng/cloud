package logan.exemple.service.monitoring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qiufeng on 2017/9/20.
 * 405 拦截
 * see {@link org.springframework.web.servlet.DispatcherServlet} find
 * 按照 AnnotationAwareOrderComparator.sort(this.handlerExceptionResolvers)
 *  排序 0 位{@link org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorAttributes}
 * Ordered.HIGHEST_PRECEDENCE
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE+1)
class HttpHandlerExcResolver implements  HandlerExceptionResolver,Ordered {
    private Logger logger = LoggerFactory.getLogger(HandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.debug("---------------------------------");
        ModelAndView mv=new ModelAndView("/error");
        request.getSession().setAttribute("err",ex.getMessage());
        mv.setStatus(HttpStatus.valueOf(response.getStatus()));
        return mv;
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+1;
    }
}
