package com.lzq.springbootredis.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program: springboot-redis
 * @description:
 * @author: liuzhenqi
 * @create: 2020-04-20 11:02
 **/
@Aspect
@Component
public class DataSourceAspect {
    @Pointcut("@annotation(com.lzq.springbootredis.config.DataSource)")
    public void dataSourcePointCut(){

    }
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point)throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);
        if(dataSource==null){
            DynamicDataSource.setDataSource("lzq-master");
        }else {
            DynamicDataSource.setDataSource(dataSource.name());
        }
        try{
            return point.proceed();
        }finally {
            DynamicDataSource.clearDataSource();
        }
    }
}
