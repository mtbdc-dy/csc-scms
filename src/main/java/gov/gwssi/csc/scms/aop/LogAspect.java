package gov.gwssi.csc.scms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

/**
 * Created by TianJ on 2017/4/10.
 */
@Component
@Aspect
public class LogAspect {
    private static Logger logger = Logger.getRootLogger();

    //记录正常调用信息
    @Before(value = "execution(* gov.gwssi.csc.scms.controller.*.*.*(..))")
    public void addBeforeLog(JoinPoint jp) {
        String targetName = jp.getTarget().getClass().toString();
        String methodName = jp.getSignature().getName();
        Object[] params = jp.getArgs();
        logger.info(targetName + ":" + methodName + " begin execution！\n");
        logger.info("params：\n");
        for (int i = 0; i < params.length; i++) {
            logger.info("param[" + i + "]:" + params[i].toString() + "\n");
        }
    }

    //记录异常信息
    @AfterThrowing(value = "execution(* gov.gwssi.csc.scms.*.*.*.*(..))", throwing = "ex")
    public void addExceptionLog(JoinPoint jp, Exception ex) {
        String targetName = jp.getTarget().getClass().toString();
        String methodName = jp.getSignature().getName();
        logger.info("ERROR_LOGGER: " + targetName + ":" + methodName + " throw Exception！\n");
        logger.error("File: " + ex.getStackTrace()[0].getFileName());
        logger.error("Method: " + ex.getStackTrace()[0].getMethodName());
        logger.error("Line：" + ex.getStackTrace()[0].getLineNumber());
        logger.error("Message：" + ex);
    }
}
