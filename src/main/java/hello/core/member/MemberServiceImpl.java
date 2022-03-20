package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;//인터페이스의 구현체를 객체로 생성해줘야함

    public MemberServiceImpl(MemberRepository memberRepository) {//생성자를 통해서 구현체를 넣는다.
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
