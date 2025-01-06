package zh.learn.spring6di.controllers.environment;

import org.springframework.stereotype.Controller;
import zh.learn.spring6di.services.environment.EnvironmentService;

@Controller
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public String getEnvironment() {
        return environmentService.getEnvironment();
    }
}
