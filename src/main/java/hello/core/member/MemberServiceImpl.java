package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //issue -> OCP 원칙과 DIP를 잘 지키지 못하고 있음! -> 구현체에 의존 중
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //다형성에 의해 MemoryMemberRepository에 있는 함수가 호출이 됨
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
