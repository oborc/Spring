package com.example.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogInceptor {

    @Pointcut(value = "@annotation(com.example.demo.aop.PrintLog)")
    private void logPointCut() {
    }

    @Before(value = "logPointCut() && @annotation(printLog)")
    public void ptint(PrintLog printLog)
    {
        System.out.println(printLog.log());
    }

}
