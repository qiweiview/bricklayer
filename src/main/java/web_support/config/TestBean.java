package web_support.config;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

    public TestBean() {
        System.out.println("bean init");
    }
}
