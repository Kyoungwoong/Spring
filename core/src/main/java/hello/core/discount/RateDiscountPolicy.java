package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10; // 10% discount

    @Override
    public int discount(Member member, int price){
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent /  100;
        }else{
            return 0;
        }
    }
}
