package name.electricalqzhang.cloud.note.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 创建一个切面组件，就是一个普通的javaBean
 */
@Component
@Aspect
public class DemoAspect {
	
	// 声明test方法将在userService的全部方法之前运行
	@Before(value = "bean(userService)")
	public void test() {
		System.out.println("hello world!");
	}
	
	@After("bean(userService)")
	public void test2() {
		System.out.println("After");
	}
	
	@AfterReturning("bean(userService)")
	public void test3() {
		System.out.println("AfterReturning");
	}
	
	@AfterThrowing("bean(userService)")
	public void test4() {
		System.out.println("AfterThrowing");
	}
	
	/**
	 * 环绕通知方法：
	 * 1. 必须有返回值Object
	 * 2. 必须有参数 ProceedingJoinPoint
	 * 3. 必须抛出异常
	 */
	@Around("bean(userService)")
	public Object test5(ProceedingJoinPoint jp) 
		throws Throwable {
		Object val = jp.proceed();
		return val;
	}
}
