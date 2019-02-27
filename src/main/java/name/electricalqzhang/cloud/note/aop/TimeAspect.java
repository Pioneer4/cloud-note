package name.electricalqzhang.cloud.note.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {
	@Around("bean(*Service)")
	public Object test(ProceedingJoinPoint jp) 
		throws Throwable {
		long t1 = System.currentTimeMillis();
		Object val = jp.proceed();
		long t2 = System.currentTimeMillis();
		long t = t2 - t1;
		Signature m = jp.getSignature();
		System.out.println(m.getName() + "用时： " + t);
		return val;
	}
}
