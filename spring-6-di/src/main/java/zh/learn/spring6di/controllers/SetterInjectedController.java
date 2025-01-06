package zh.learn.spring6di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import zh.learn.spring6di.services.GreetingService;

@Controller
public class SetterInjectedController {

    private GreetingService greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }

    @Autowired
    public void setGreetingService(
            @Qualifier("setterGreetingBean") GreetingService greetingService
    ) {
        System.out.println("SetterInjectedController.setGreetingService");
        this.greetingService = greetingService;
    }
}
