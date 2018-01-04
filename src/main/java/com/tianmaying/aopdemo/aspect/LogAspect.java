package com.tianmaying.aopdemo.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* com.tianmaying.aopdemo..*.bookFlight(..))") //2
	private void logPointCut() {
	}

	@AfterReturning(pointcut = "logPointCut()", returning = "retVal") //3
	public void logBookingStatus(boolean retVal) {
		if (retVal) {
			System.out.println("booking flight succeeded!");
		} else {
			System.out.println("booking flight failed!");
		}
	}
}
