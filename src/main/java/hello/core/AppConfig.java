package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {//애플리케이션 전체를 설정하고 구성
    //Appconfig 를 통해서 인터페이스의 구현체를 주입시킨다.
    //AppcConfig를 통해서 추상 클래스(MemberService, OrderService)를 생성한다.
    //할인정책의 수정 등이 있을 경우 다른 부분말고 AppConfig코드만 수정하면 된다.

    //AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것: IoC컨테이너, DI컨테이너너

    @Bean//스프링컨테이너가 빈 저장소에 저장한다.
    public MemberService memberService() { //빈 이름(key): 메서드명(임의로 부여가능), 빈 객체: 반환되는 객체
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {//반환값에 (객체반환하는)메서드 입력
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();//구현객체입니다!
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());//반환값에 (객체반환하는)메서드 입력
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();//구현객체입니다!
    }


}
