package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

//주문서비스 클라이언트
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;//정적인 의존관계
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();//클라이언트가 추상과 구현체 모두 의존함(DIP위반) + 할인정책클래스를 바꿀 때 클라이언트 코드가 바뀐다. (OCP위반)
    private final DiscountPolicy discountPolicy;//정적인 의존관계

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
