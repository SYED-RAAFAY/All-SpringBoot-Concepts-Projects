package com.example.ShoppingCartProject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProductControllerAspect {
    @Around("execution(* com.example.ShoppingCartProject.product.ProductController.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Proceed with the original method execution
        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;

        // You can perform additional actions here, for example, logging or auditing
        joinPoint.getArgs();
        System.out.println("Method " + joinPoint.getSignature().getName()+
                " executed in " + executionTime + "ms");

        return result;
    }
}
