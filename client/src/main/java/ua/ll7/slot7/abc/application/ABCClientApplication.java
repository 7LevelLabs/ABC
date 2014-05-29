package ua.ll7.slot7.abc.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alex Velichko
 *         28.05.14 : 18:17
 */
public class ABCClientApplication {

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");

		ActionBean actionBean = appContext.getBean("actionBean", ActionBean.class);

		actionBean.action();
	}
}
