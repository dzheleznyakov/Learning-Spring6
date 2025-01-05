package zh.learn.spring6di.controllers;

import zh.learn.spring6di.services.GreetingService;

public class SetterInjectedController {
    private GreetingService greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
