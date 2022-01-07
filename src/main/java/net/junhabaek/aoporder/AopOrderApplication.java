package net.junhabaek.aoporder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(order = Ordered.LOWEST_PRECEDENCE)
//@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE)
public class AopOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopOrderApplication.class, args);
    }

}
