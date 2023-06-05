package hello.core.member;

public class MemberServiceImpl implements MemberService {
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
     ** 저장소를 가져오기
     ** MemberRepository만 가져올 시 NullPoint
     */
    private MemberRepository memberRepository;
    // 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member){
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }
}
