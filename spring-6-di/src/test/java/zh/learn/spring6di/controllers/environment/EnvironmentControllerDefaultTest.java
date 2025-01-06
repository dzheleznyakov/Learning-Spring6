package zh.learn.spring6di.controllers.environment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnvironmentControllerDefaultTest {

    @Autowired
    EnvironmentController environmentController;

    @Test
    void getEnvironment() {
        System.out.println(environmentController.getEnvironment());
    }
}