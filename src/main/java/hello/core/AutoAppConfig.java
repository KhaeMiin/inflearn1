package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@Component이 붙은 클래스를 찾아서 자동으로 빈 등록을 해준다.
@ComponentScan(
        basePackages = "hello.core",//이런식으로 패키지 범위를 정할 수 있다. 생략시 현 클래스의 패키지를 위치로
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)//@Configuration붙은 클래스 뺄 것 지정
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
