package roseindia.net.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class SimpleAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] objects, Object object) throws Throwable {
		System.out.println("Currently Processing " + method);
	}

}
