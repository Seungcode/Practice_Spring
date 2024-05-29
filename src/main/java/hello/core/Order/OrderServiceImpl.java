package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    //인터페이스에만 의존하기 위한 변 -> 오잉 그러면 전 뭐가 되는 걸까 -> 누군가 구현 객체를 알아서 생성하고 주입해줘야한다! -> AppConfig
    private final DiscountPolicy discountPolicy;
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    /*
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    위와 같이 사용항 경우 SOLID 중 개방-폐쇄 원칙과 의존관계 역전 원칙을 지키지 못함
    -> 바꾸려면 결국 이 부분을 건드려야 하기 때문 -> 즉 인터페이스가 타 인터페이스의 구현까지 의존하는 상태임
    */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
