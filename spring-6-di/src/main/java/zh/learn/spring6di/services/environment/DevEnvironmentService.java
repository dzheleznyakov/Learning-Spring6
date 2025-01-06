package zh.learn.spring6di.services.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"env_dev", "default"})
@Service
public class DevEnvironmentService implements EnvironmentService {
    @Override
    public String getEnvironment() {
        return "dev";
    }
}
