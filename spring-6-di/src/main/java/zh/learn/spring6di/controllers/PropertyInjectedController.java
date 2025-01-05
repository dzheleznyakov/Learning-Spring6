package zh.learn.spring6di.controllers;

import zh.learn.spring6di.services.GreetingService;

public class PropertyInjectedController {

    GreetingService greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}
