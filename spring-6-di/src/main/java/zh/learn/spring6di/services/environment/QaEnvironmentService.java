package zh.learn.spring6di.services.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("env_qa")
@Service
public class QaEnvironmentService implements EnvironmentService {
    @Override
    public String getEnvironment() {
        return "qa";
    }
}
