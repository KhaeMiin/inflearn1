package hello.core.member;

public interface MemberRepository {//회원저장소

    void save(Member member);//저장

    Member findById(Long memberId);//아이디검색
}
