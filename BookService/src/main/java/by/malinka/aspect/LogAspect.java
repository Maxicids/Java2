package by.malinka.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {
    static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * by.malinka.controller.impl.UserControllerImpl.*(..))")
    public void callAtGeneralController() {}
    @Before("callAtGeneralController()")
    public void beforeCallGeneral(JoinPoint jp) {logger.info("Trying to access: " + jp.getSignature());}
    @AfterReturning("callAtGeneralController()")
    public void afterReturningCallGeneral(JoinPoint jp) {
        logger.info("Success: " + jp.getSignature());
    }
    @AfterThrowing("callAtGeneralController()")
    public void afterThrowingCallGeneral(JoinPoint jp) {
        logger.info("Error " + jp.getSignature());
    }


    @Pointcut("execution(public * by.malinka.controller.impl.BookControllerImpl.*(..))")
    public void callAtUserController() {}
    @Before("callAtUserController()")
    public void beforeCallUser(JoinPoint jp) { logger.info("Trying to access: " + jp.getSignature());}
    @AfterReturning("callAtUserController()")
    public void afterReturningCallUser(JoinPoint jp) { logger.info("Success: " + jp.getSignature());}
    @AfterThrowing("callAtUserController()")
    public void afterThrowingCallUser(JoinPoint jp) { logger.info("Error: " + jp.getSignature());}



    @Pointcut("execution(public * by.malinka.controller.impl.CartControllerImpl.*(..))")
    public void callAtAdminController() {}
    @Before("callAtAdminController()")
    public void beforeCallAdmin(JoinPoint jp) { logger.info("Trying to access: " + jp.getSignature());}
    @AfterReturning("callAtAdminController()")
    public void afterReturningCallAdmin(JoinPoint jp) { logger.info("Success: " + jp.getSignature());}
    @AfterThrowing("callAtAdminController()")
    public void afterThrowingCallAdmin(JoinPoint jp) { logger.info("Error: " + jp.getSignature());}

}
