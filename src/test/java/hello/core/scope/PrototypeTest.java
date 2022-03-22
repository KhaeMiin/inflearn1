package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);//자동으로 @ComponentScan됨
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

//        ac.close();//스프링 컨테이너가 의존관계 주입까지만 관여하지 때문에 실행되지 않음
        prototypeBean1.destroy();//이런식으로 종료메서드를 직접 호출해줘야한다.(@PreDectory가 전혀 실행되지않음)

    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct//시작시 의존관계 주입 후 실행
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy//종료시 실행
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
