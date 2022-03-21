package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//주문서비스 클라이언트
@Component
//@RequiredArgsConstructor//필수값인 final이 붙은 걸 가지고 생성자를 만들어줌(자동의존관계까지 주입)
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;//정적인 의존관계 (final: 무조건 값이 있어야한다)
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();//클라이언트가 추상과 구현체 모두 의존함(DIP위반) + 할인정책클래스를 바꿀 때 클라이언트 코드가 바뀐다. (OCP위반)
    private final DiscountPolicy discountPolicy;//정적인 의존관계

    //    @Autowired //생성자가 하나일 경우는 생략이 가능하다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);//정적인 의존관계
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
