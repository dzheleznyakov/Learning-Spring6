package zh.learn.spring6di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import zh.learn.spring6di.controllers.MyController;

@SpringBootApplication
public class Spring6DiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Spring6DiApplication.class, args);

		MyController controller = ctx.getBean(MyController.class);

		System.out.println("In Main Method");
		System.out.println(controller.sayHello());
	}

}
