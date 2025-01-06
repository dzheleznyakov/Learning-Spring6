package zh.learn.spring6di.services.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("env_uat")
@Service
public class UatEnvironmentService implements EnvironmentService {
    @Override
    public String getEnvironment() {
        return "uat";
    }
}
