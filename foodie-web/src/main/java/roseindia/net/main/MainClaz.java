package roseindia.net.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import roseindia.net.bean.DependencyBean;
import roseindia.net.bean.SimpleBean;

public class MainClaz {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/config.xml");
		DependencyBean simpleBean1 = (DependencyBean) context.getBean("myDependency2");
		simpleBean1.sayHi();

	}
}
