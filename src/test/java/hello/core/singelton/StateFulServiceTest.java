package hello.core.singelton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        //ThreadA: A사용자가 10000원 주문
        int userAPrice = stateFulService1.order("userA", 10000);
        //ThreadB: B사용자가 20000원 주문
        int userBPrice = stateFulService1.order("userB", 20000);

        //ThreadA: 사용자 A가 주문 금액 조회
//        int price = stateFulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(stateFulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }

}