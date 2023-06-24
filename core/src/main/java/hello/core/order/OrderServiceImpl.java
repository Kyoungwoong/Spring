package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // lombok을 이용한 final 필드를 모아서 생성자 생성.
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 아래 두 줄은 DIP 위반코드
    // 고정 할인
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 비율 할인
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // DIP를 지키기 위한 코드
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;

    // 수정자 주입을 하기 위한 final keyword 삭제
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

    // 필드 주입
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy;

    // 생성자 주입
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final DiscountPolicy discountPolicy;

    // Autowired 생략 가능. 생성자가 하나면
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    // 수정자 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
}
