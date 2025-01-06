package zh.learn.spring6di.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import zh.learn.spring6di.controllers.MyController;

@Component
public class LifeCycleDemoBean implements InitializingBean, DisposableBean, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware, BeanPostProcessor {

    public LifeCycleDemoBean() {
        System.out.println("## I'm in the LifeCycleDemoBean Constructor ##");
    }

    private String javaVer;

    @Value("${java.specification.version}")
    public void setJavaVer(String javaVer) {
        this.javaVer = javaVer;
        System.out.println("## 1 Properties Set - Java Ver: " + this.javaVer);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("## 2 BeanNameAware - My Bean Name is: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("## 3 BeanFactoryAware - My Factory has been set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("## 4 ApplicationContextAware - Application context has been set");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("## 5 postConstruct - The @PostConstruct annotated method has been called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("## 6 afterPropertiesSet Populate Properties - The LifeCycleDemoBean has its properties set");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("## 7 preDestroy - The @PreDestroy annotated method has been called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("## 8 DisposableBean - The LifeCycleDemoBean has been terminated");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("## postProcessBeforeInitialization - " + beanName);

        if (bean instanceof MyController demoBean) {
            System.out.println("Calling before init");
            demoBean.beforeInit();
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("## postProcessAfterInitialization - " + beanName);

        if (bean instanceof MyController demoBean) {
            System.out.println("Calling after init");
            demoBean.afterInit();
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
