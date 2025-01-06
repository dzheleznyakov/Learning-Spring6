package zh.learn.spring6di.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LifeCycleDemoBeanTest {

    @Autowired
    LifeCycleDemoBean lifeCycleDemoBean;

    @Test
    void testBeanLifeCycle() {
    }
}