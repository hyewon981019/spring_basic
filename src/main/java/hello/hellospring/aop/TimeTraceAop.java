package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component// 빈으로 등록 또는 SpringConfig에서 직접 등록
public class TimeTraceAop {
    
    @Around("execution(* hello.hellospring..*(..))")//해당 패키지명 하위들에 공통 관심 사항을 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("start : " + joinPoint.toString()); //함수이름
        try{
            Object result = joinPoint.proceed();
            return result;

        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("end : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
