package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {


    MemberService memberService;
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @BeforeEach //모든 @Test 실행전 반드시 실행됨
    public void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
        memberService = ac.getBean("memberService", MemberService.class);
    }

    @Test
    void join() {
        //given: ~한 일이 주어졌을때
        Member member = new Member(1L, "memberA", Grade.VIP);//이런 맴버를 만들었을떄

        //when: 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);//위에 만든 아이디를 조회

        //then: 이렇게 된다.
        Assertions.assertThat(member).isEqualTo(findMember);//member은 (findMember)와 똑같으면 통과

    }
}
